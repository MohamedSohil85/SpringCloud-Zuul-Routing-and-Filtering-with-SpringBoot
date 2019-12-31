package com.example.apigateway.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Errorfilter extends ZuulFilter {
    private Logger log=LoggerFactory.getLogger(Errorfilter.class);
    @Override
    public String filterType() {
        return "Error";
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
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();

        String response = ctx.getResponseBody();
        log.info("Error occurred, Response  = {}, ", response);
        return null;
    }
}
