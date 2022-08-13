package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.TokenGenerateService;

@Component
public class AuthenticationTokenFilter implements Filter{
	


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String url = req.getRequestURL().toString();
		
		System.out.println("Incoming request:->"+url);
		
		if(url.contains("/public/")) {
			chain.doFilter(request, response);
		}else {
//			Token Authentication
			
			String authToken = req.getHeader("authToken");
			
			if (authToken == null || authToken.trim().length() != 16) {
				HttpServletResponse res = (HttpServletResponse) response;
				res.setContentType("application/json");
				res.setStatus(401);
				res.getWriter().write("{'msg':'Please Login before access service'}");
			}else {
				chain.doFilter(request, response);
			}
		}
	}

}
