package com.apresentacaoaws.security;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apresentacaoaws.constant.SecurityConstants;
import com.apresentacaoaws.dto.UserLoginResponseDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {

	public UserLoginResponseDto createToken(String email, List<String> roles) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, SecurityConstants.JWT_EXP_DAY);
		
		String jwt = Jwts.builder()
				.setSubject(email)
				.setExpiration(calendar.getTime())
				.claim(SecurityConstants.JWT_ROLE_KEY, roles)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.API_KEY.getBytes())
				.compact();
		
		Long expireIn = calendar.getTimeInMillis();
		
		return new UserLoginResponseDto(jwt, expireIn, SecurityConstants.JWT_PROVIDER);
	}
	
	public Claims parceToken(String jwt) throws JwtException {
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstants.API_KEY.getBytes())
				.parseClaimsJws(jwt)
				.getBody();
		
		return claims;
	}
	
}
