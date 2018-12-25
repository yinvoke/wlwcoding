package com.microservice.backend.config;

import com.microservice.backend.entity.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
*  添加跨域访问的header
*/
@Component
@WebFilter(urlPatterns="/*",filterName="domainFilter")
public class DomainFileter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{}


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        //便于调试的代码,上线的时候需要注释掉
//        HttpSession session = request.getSession();
//        User user = new User("debug",null,null,null);
//        session.setAttribute("user",user);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
