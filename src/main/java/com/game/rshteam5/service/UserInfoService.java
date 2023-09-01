package com.game.rshteam5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.rshteam5.mapper.UserInfoMapper;
import com.game.rshteam5.util.JWTToken;
import com.game.rshteam5.vo.UserInfoVO;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoMapper uiMapper;
	@Autowired
	private JWTToken jwtToken;
	
	public List<UserInfoVO> getUserInfos(UserInfoVO user) {
		return uiMapper.selectUserInfos(user);
	}
	
	public UserInfoVO getUserInfo(int uiNum) {
		return uiMapper.selectUserInfo(uiNum);
	}
	
	public int insertUserInfo(UserInfoVO user) {
		return uiMapper.insertUserInfo(user);
	}
	
	public int updateUserInfo(UserInfoVO user) {
		return uiMapper.updateUserInfo(user);
	}
	
	public int deleteUserInfo(int uiNum) {
		return uiMapper.deleteUserInfo(uiNum);
	}
	
	public UserInfoVO login(UserInfoVO user) {
		user = uiMapper.selectByIdAndPwd(user);
		if(user!=null) {
			String token = jwtToken.getToken(user.getUiId());
			user.setToken(token);
			user.setUiPwd(null);
		}
		return user;
	}
}
