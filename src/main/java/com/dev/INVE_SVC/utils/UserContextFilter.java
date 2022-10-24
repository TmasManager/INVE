package com.dev.INVE_SVC.utils;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserContextFilter implements Filter {

    @Autowired
    TrackingFilter trackingFilter;
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        
        Enumeration<String> em = httpServletRequest.getHeaderNames();

        UserContextHolder.getContext();
		UserContext.setCorrelationId(  httpServletRequest.getHeader(UserContext.CORRELATION_ID) );
        UserContextHolder.getContext();
		UserContext.setUserId( trackingFilter.getAuthenticationName(httpServletRequest.getHeader(UserContext.AUTH_TOKEN), UserContext.USER_ID));
        UserContextHolder.getContext();
        UserContext.setUserName( trackingFilter.getAuthenticationName(httpServletRequest.getHeader(UserContext.AUTH_TOKEN), UserContext.USER_NAME));
        UserContextHolder.getContext();
		UserContext.setAuthToken( httpServletRequest.getHeader(UserContext.AUTH_TOKEN) );
        
        System.out.println("correlation_id : " + UserContext.getCorrelationId());
        System.out.println("token     : " + UserContext.getAuthToken());
        System.out.println("user_id   : " + UserContext.getUserId());
        System.out.println("user_name : " + UserContext.getUserName());
        
        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
