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

public class ProductSecurityFilter implements Filter {

    private SecurityService securityService = ServiceLocator.getService(DefaultSecurityService.class);;

    public ProductSecurityFilter(SecurityService defaultSecurityService) {
        this.securityService = defaultSecurityService;
    }

    // chain of responsibility
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Cookie[] cookies = httpServletRequest.getCookies();
        String   requestObject = httpServletRequest.getRequestURI().replaceFirst("^/", "");
        boolean isAuth = false;

        Session session = securityService.getSession(cookies, "user-token");

        if (session != null) {

            if ( securityService.isAuthorized(session.getUser().getRole(), requestObject) ) {
                chain.doFilter(request, response);
            }
            else {
               httpServletResponse.sendRedirect("/login");
            }
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
