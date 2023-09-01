package com.game.rshteam5.util;

import java.security.Key;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.game.rshteam5.vo.UserInfoVO;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
//@ConfigurationProperties("classpath:jwt.properties")	// 굳이 이렇게 안 나누고 그냥 jwt.token.key를 application.properties에 써도 되는데 application.properties가 이해할 수 없는(그냥 내가 쓰려고 직접 만든거) 이런 것들은 나눠서 쓰는 경우도 있음
public class JWTToken {
	private final String JWT_TOKEN_KEY;
	private final Long JWT_TOKEN_EXPIRE;
	
	public JWTToken(@Value("${jwt.token.key}") String jwtTokenKey, @Value("${jwt.token.expire}") Long jwtTokenExpire) {
		this.JWT_TOKEN_KEY = jwtTokenKey;
		this.JWT_TOKEN_EXPIRE = jwtTokenExpire;
	}
	
	public String getJwtToken() {
		return JWT_TOKEN_KEY;
	}
	
	public Long getJwtTokenExpire() {
		return JWT_TOKEN_EXPIRE;
	}
	
	public String getToken(String uiId) {
		Date date = new Date();
		long expireDate = date.getTime() + JWT_TOKEN_EXPIRE;
		
		Key key = Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes());
		log.info("key=>{}", key);
		String token = Jwts.builder()
		.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
		.setSubject(uiId)
		.setIssuedAt(date)
		.setExpiration(new Date(expireDate))
		.signWith(key, SignatureAlgorithm.HS256)
		.compact();
		
		return token;
	}
	
	public UserInfoVO validToken(String token) {
		Key key = Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes());
		try {
			String userId = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			log.info("userId=>{}", userId);
		} catch(ExpiredJwtException eje) {
			log.error("expired");
		} catch(SignatureException se) {
			log.error("invalid signature");
		}
		return null;			
	}
}
