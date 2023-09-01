package com.game.rshteam5;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenTest {
	private static final Long EXPIRE_TIME = 2000L;

	public static void main(String[] args) {
		JwtBuilder jb = Jwts.builder();
		Date date = new Date();
		Date expireDate = new Date(date.getTime() + EXPIRE_TIME);
		String keyStr = "11111111112222222222333333333344";
		Key key = Keys.hmacShaKeyFor(keyStr.getBytes());
		String token = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setSubject("id")
				.setIssuedAt(date)
				.setExpiration(expireDate)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		log.info("token => {}", token);
		
		String subject = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		System.out.println(subject);
	}
}
