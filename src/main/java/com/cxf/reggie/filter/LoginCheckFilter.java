package com.cxf.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.cxf.reggie.common.BaseContext;
import com.cxf.reggie.common.R;
import com.cxf.reggie.entity.Employee;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter extends HttpFilter {
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String[] excludeUrls = {"/employee/login", "/backend/**", "/front/**","/employee/logout",
                "/druid/**","/user/sendMsg","/user/login","/car/**"};
        if (check(excludeUrls, requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        Object empId = request.getSession().getAttribute("employee");

        if(empId!=null){
            BaseContext.setCurrentUserId((Long)empId);
            chain.doFilter(request, response);
            return;
        }

        Object userId = request.getSession().getAttribute("user");
        if(userId!=null){
            BaseContext.setCurrentUserId((Long)userId);
            chain.doFilter(request, response);
            return;
        }



        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

    }

    public boolean check(String[] excludeUrls, String requestURI) {
        for (String excludeUrl : excludeUrls) {
            if (antPathMatcher.match(excludeUrl, requestURI)) {
                return true;
            }
        }
        return false;
    }
}
