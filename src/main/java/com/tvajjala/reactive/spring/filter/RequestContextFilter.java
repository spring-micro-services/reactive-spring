package com.tvajjala.reactive.spring.filter;

import com.tvajjala.reactive.spring.context.ThreadContext;
import com.tvajjala.reactive.spring.context.ThreadContextHolder;
import org.springframework.http.HttpHeaders;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

import static com.tvajjala.reactive.spring.context.ThreadContextHolder.getThreadContext;

public class RequestContextFilter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;

        HttpHeaders httpHeaders=new HttpHeaders();

        Enumeration<String> enumeration=httpServletRequest.getHeaderNames();

        while(enumeration.hasMoreElements()){

            String name =enumeration.nextElement();
            if(name.startsWith("X-") || name.startsWith("x-")) {//custom header starts with x will be added to threadContext
                httpHeaders.add(name, httpServletRequest.getHeader(name));
            }

        }



        getThreadContext().setHttpHeaders(httpHeaders);



        filterChain.doFilter(servletRequest,servletResponse);


        /** clear context once processing done */
        ThreadContextHolder.clear();
    }
}
