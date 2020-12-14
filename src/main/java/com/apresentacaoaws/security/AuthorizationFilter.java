package com.apresentacaoaws.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.apresentacaoaws.constant.SecurityConstants;
import com.apresentacaoaws.service.exception.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (jwt == null || !jwt.startsWith(SecurityConstants.JWT_PROVIDER)) {
			ApiError apierror = new ApiError(HttpStatus.UNAUTHORIZED.value(),SecurityConstants.JWT_INVALID_MSG,new Date());
			PrintWriter printWriter = response.getWriter(); 
			
			ObjectMapper mapper = new ObjectMapper();
			String apiErrorString = mapper.writeValueAsString(apierror);
			
			printWriter.write(apiErrorString);
		}
	}

}
