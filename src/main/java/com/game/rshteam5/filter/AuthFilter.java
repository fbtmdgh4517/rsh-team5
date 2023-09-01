package com.game.rshteam5.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;

import com.game.rshteam5.util.JWTToken;
import com.game.rshteam5.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

//@WebFilter("/*")	// 이렇게 사용하는거 추천 안한다고 함
@WebFilter(value = {"/", "/views/*", "/api/*"})
@Slf4j
public class AuthFilter extends GenericFilterBean {		// 추상 클래스는 메소드가 선언만 되어있는 경우가 있어서(인터페이스랑 비슷) 상속받는 경우에 오버라이딩을 해줘야됨
	@Autowired
	private JWTToken jwtToken;
	
//	@Value("${auth.exclude.urls}")
	private List<String> excludeUrls = new ArrayList<>();
	{
		excludeUrls.add("/views/login");
		excludeUrls.add("/views/join");
		excludeUrls.add("/api/login");
		excludeUrls.add("/api/join");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request instanceof HttpServletRequest req && response instanceof HttpServletResponse res) {
			String uri = req.getRequestURI();
			if(!excludeUrls.contains(uri)) {
				HttpSession session = req.getSession();
				UserInfoVO user = (UserInfoVO) session.getAttribute("user");
				if(user == null) {
					res.sendRedirect("/views/login");
					return;
				}
			}
		}
	}
	
}
