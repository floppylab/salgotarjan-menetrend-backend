package com.floppylab.salgotarjanschedule.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomCorsFilter implements Filter {

    @Autowired
    Environment environment;

    public CustomCorsFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        HttpServletRequest request = (HttpServletRequest) req;

//        if (Arrays.stream(this.environment.getActiveProfiles()).anyMatch(env -> (env.equalsIgnoreCase("development")))) {
//            response.setHeader("Access-Control-Allow-Origin", "http://dev.yourdomain.com");
//        } else if (Arrays.stream(this.environment.getActiveProfiles()).anyMatch(env -> (env.equalsIgnoreCase("production")))) {
//            response.setHeader("Access-Control-Allow-Origin", "https://www.yourdomain.com");
//        } else {
        response.setHeader("Access-Control-Allow-Origin", "*");
//        }

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Expose-Headers", "Authorization, username, name, role");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
