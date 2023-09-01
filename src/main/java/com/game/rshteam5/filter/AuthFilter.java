package com.game.rshteam5.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import lombok.extern.slf4j.Slf4j;

//@WebFilter("/*")	// 이렇게 사용하는거 추천 안한다고 함
@Slf4j
public class AuthFilter extends GenericFilterBean {		// 추상 클래스는 메소드가 선언만 되어있는 경우가 있어서(인터페이스랑 비슷) 상속받는 경우에 오버라이딩을 해줘야됨

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		Enumeration<String> names = req.getHeaderNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			log.info("name=>{}", name);
			log.info("value=>{}", req.getHeader(name));
		}
		chain.doFilter(request, response);
	}
	
}
