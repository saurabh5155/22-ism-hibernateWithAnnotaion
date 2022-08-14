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

import com.bean.UserBean;
import com.dao.UserDao;

@Component
public class AuthenticationTokenFilter implements Filter {

	@Autowired
	UserDao userDao;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURL().toString();

		System.out.println("Incoming request:->" + url);

		if (url.contains("/public/")) {
			chain.doFilter(request, response);
		} else {
//			Token Authentication

			String authToken = req.getHeader("authToken");

			System.out.println(authToken);

			if (authToken == null || authToken.trim().length() != 16) {
				HttpServletResponse res = (HttpServletResponse) response;
				res.setContentType("application/json");
				res.setStatus(401);
				res.getWriter().write("{'msg':'Please Login before access service'}");
			} else {
				UserBean user = null;
				try {					
					 user = userDao.findByAuthenticationToken(authToken);
				} catch (Exception e) {
					HttpServletResponse res = (HttpServletResponse) response;
					res.setContentType("application/json");
					res.setStatus(401);
					res.getWriter().write("{'msg':'Login Again'}");
				}
				if (user == null) {
					HttpServletResponse res = (HttpServletResponse) response;
					res.setContentType("application/json");
					res.setStatus(401);
					res.getWriter().write("{'msg':'Login Again'}");
				} else {
					if (user.getAuthenticationToken().equals(authToken)) {
						System.out.println("Authenticate Successfull");
						chain.doFilter(request, response);
					} else {
						HttpServletResponse res = (HttpServletResponse) response;
						res.setContentType("application/json");
						res.setStatus(401);
						res.getWriter().write("{'msg':'Login Again'}");
					}
				}
			}
		}
	}

}
