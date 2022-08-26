package com.filter;

import java.io.IOException;
import java.util.Enumeration;

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

		System.out.println("---------------------------------------------"+req.getParameter("authToken"));
		Enumeration<String> allHeaders = req.getHeaderNames();
		System.out.println("***************");
		while (allHeaders.hasMoreElements()) {
			String hName = allHeaders.nextElement();
			System.out.println(hName+" => "+req.getHeader(hName));
		}
		System.out.println("***************");
		System.out.println("---------------------------------------------");
		
		
		if (url.contains("/public/") || req.getMethod().toLowerCase().equals("options")) {
			System.out.println("Public");
			chain.doFilter(request, response);
			
		} else {
//			Token Authentication
			System.out.println("private");
			String authToken = req.getHeader("authToken");
			
			
//			String authToken = "2jUODHp6dlm5fY27";

			System.out.println("By Url"+authToken);

			if (  authToken == null || authToken.trim().length() != 16) {
				System.err.println("sending 401");
				HttpServletResponse res = (HttpServletResponse) response;
				res.setContentType("application/json");
				res.setStatus(200);
				res.getWriter().write("{'msg':'Please Login before access service'}");
			} else {
				System.out.println("***********************TOKEN*******************");
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
