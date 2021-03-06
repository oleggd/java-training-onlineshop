package com.study.onlineshop.web.filters;

import com.study.onlineshop.entity.Session;
import com.study.onlineshop.service.SecurityService;
import com.study.onlineshop.service.ServiceLocator;
import com.study.onlineshop.service.impl.DefaultSecurityService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSecurityFilter implements Filter {
    private SecurityService securityService = ServiceLocator.getService(DefaultSecurityService.class);

    public UserSecurityFilter() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    // chain of responsibility
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Cookie[] cookies = httpServletRequest.getCookies();
        String requestObject = httpServletRequest.getRequestURI();
        if (!requestObject.equals("/")) {
            requestObject = httpServletRequest.getRequestURI().replaceFirst("^/", "");
        }
        boolean isAuth = false;
        Session session = securityService.getSession(cookies, "user-token");

        if (session != null) {
            if ( securityService.isAuthorized(session.getUser().getRole(), requestObject) ) {
                chain.doFilter(request, response);
            }
            else {
                if ( requestObject.equals("products") || requestObject.equals("logout")) {
                    httpServletResponse.sendRedirect("/login");
                } else {
                    httpServletResponse.sendRedirect("/");
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
