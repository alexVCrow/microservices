package ua.alexcrow.zullapigateway;

import brave.Tracer;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class PostFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Tracer tracer;

    @Override
    public String filterType() {
        return "post";
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
        System.out.println(RequestContext.getCurrentContext().getZuulRequestHeaders());
        RequestContext ctx = RequestContext.getCurrentContext();
        System.out.println("============ " + ctx.getResponse().getStatus());
        logger.info("response method -> {} response method -> {} response uri -> {} response -> {} ", ctx.getResponseStatusCode(), ctx.getRequest().getMethod(), ctx.getRequest().getRequestURI());
        try (final InputStream responseDataStream = ctx.getResponseDataStream()) {
            final String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
            logger.info("response method -> {} response method -> {} response uri -> {} response -> {} ", ctx.getResponseStatusCode(), ctx.getRequest().getMethod(), responseData);
            ctx.setResponseBody(responseData);
        } catch (IOException e) {
            logger.info("Error reading body",e);
        }
        return null;
    }
}
