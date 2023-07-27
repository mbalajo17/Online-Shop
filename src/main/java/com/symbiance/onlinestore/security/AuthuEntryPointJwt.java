//package com.symbiance.onlinestore.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//@Component
//public class AuthuEntryPointJwt implements AuthenticationEntryPoint {
//    private static final Logger logger = LoggerFactory.getLogger(AuthuEntryPointJwt.class);
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
//            throws IOException, ServletException {
//        logger.error("UnAuthoriced Error:{}",authException.getMessage());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error:UnAuthoriced");
//
//
//    }
//}
