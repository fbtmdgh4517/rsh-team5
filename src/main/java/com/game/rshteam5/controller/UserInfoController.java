package com.game.rshteam5.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.rshteam5.service.UserInfoService;
import com.game.rshteam5.util.JWTToken;
import com.game.rshteam5.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class UserInfoController {
	@Autowired
	private UserInfoService uiService;
	@Autowired
	private JWTToken jwtToken;
	
	@GetMapping("/valid")
	public UserInfoVO valid(@RequestParam("token") String token) {
		return jwtToken.validToken(token);
	}
	
	@GetMapping("/expire")
	public Long getExpire() {
		return jwtToken.getJwtTokenExpire();
	}
	
	@GetMapping("/user-infos")
	public List<UserInfoVO> getUserInfos(HttpServletRequest req, UserInfoVO user) {		// 번호로 검색하는 경우에 이거랑 밑에거 차이는 얘는 배열(복수)로 보여주고, 밑에거는 한개만 보여줌
		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		jwtToken.getUserIdFromToken(token);
		log.info("user=>{}", user);
		return uiService.getUserInfos(user);
	}
	
	@GetMapping("/user-infos/{uiNum}")
	public UserInfoVO getUserInfo(@PathVariable int uiNum) {	// 파라미터가 2개 이상일때는 @PathVariable("uiNum") int uiNum처럼 직접 명시를 하거나 파라미터 순서대로 적으면 된다. 한개일때는 상관 무
		log.info("uiNum=>{}", uiNum);
		return uiService.getUserInfo(uiNum);
	}
	
	@PostMapping("/login")
	public UserInfoVO login(@RequestBody UserInfoVO user) {
		log.info("user=>{}", user);
		return uiService.login(user);
	}
	
	@PostMapping("/user-infos")
	public int insertUserInfo(@RequestBody UserInfoVO user) {	//파라미터 괄호 한에 바로 왼쪽에 @modelattribute 생략되어있음, 프론트에서 입력한거롤 백엔드로 json으로 보낼거면 @RequestBody를 써줘야됨, form으로 보낼거면 안 써도 되는듯?이건 모르겠음 formdata라고 하셨음
		log.info("user=>{}", user);
		return uiService.insertUserInfo(user);
	}
	
	@PutMapping("/user-infos")
	public int updateUserInfo(@RequestBody UserInfoVO user) {
		log.info("user=>{}", user);
		return uiService.updateUserInfo(user);
	}
	
	@DeleteMapping("/user-infos/{uiNum}")
	public int deleteUserInfo(@PathVariable int uiNum) {
		log.info("uiNum=>{}", uiNum);
		return uiService.deleteUserInfo(uiNum);
	}
	
}
