package com.example.apigateway.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
@Configuration
public class PreFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(PreFilter.class);
    @Override
    public String filterType() {
        return "Prepost";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("PreFilter: " + String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        return null;
    }
}
