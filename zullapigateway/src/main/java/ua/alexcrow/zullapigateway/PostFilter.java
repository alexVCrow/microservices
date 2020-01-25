package ua.alexcrow.zullapigateway;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class PostFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
        System.out.println("Inside Response Filter");
        RequestContext ctx = RequestContext.getCurrentContext();
        try (final InputStream responseDataStream = ctx.getResponseDataStream()) {
            final String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
            logger.warn("response -> {} ", responseData);
            ctx.setResponseBody(responseData);
        } catch (IOException e) {
            logger.warn("Error reading body",e);
        }
        return null;
    }
}
