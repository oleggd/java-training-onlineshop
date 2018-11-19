package com.study.onlineshop.web.filters;

import com.study.onlineshop.entity.Session;
import com.study.onlineshop.service.SecurityService;
import com.study.onlineshop.service.impl.DefaultSecurityService;
import com.study.onlineshop.web.templater.PageGenerator;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminSecurityFilter implements Filter {

    private SecurityService securityService;

    public AdminSecurityFilter() {}

    // chain of responsibility
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Cookie[] cookies = httpServletRequest.getCookies();
        String requestURI = httpServletRequest.getRequestURI();

        String  requestObject = "";

        if (requestURI.equals("/product/add")) {
            requestObject = "add";
        } else if (requestURI.equals("/product/edit")) {
            requestObject = "edit";
        } else if (requestURI.equals("/product/delete")) {
            requestObject = "delete";
        }

        boolean isAuth = false;

        Session session = securityService.getSession(cookies, "user-token");

        if (session != null) {

            if ( securityService.isAuthorized(session.getUser().getRole(), requestObject) ) {
                chain.doFilter(request, response);
            }
            else {
                PageGenerator pageGenerator = PageGenerator.instance();
                String page = pageGenerator.getPage("auth_err", null);
                response.getWriter().write(page);
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpServletResponse.sendRedirect("/products");
            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        WebApplicationContext webApplicationContext =  WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        securityService = webApplicationContext.getBean(DefaultSecurityService.class);
    }

}
