package ua.alexcrow.zullapigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;
import brave.Tracer;

@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Tracer tracer;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext rcc = RequestContext.getCurrentContext();
		rcc.addZuulRequestHeader("traceId", tracer.currentSpan().context().traceIdString());
		rcc.addZuulRequestHeader("spanId", tracer.currentSpan().context().spanIdString());
		HttpServletRequest request = rcc.getRequest();
		System.out.println(request.getHeader("embid"));
		System.out.println(request.getHeader("session"));
		if ("POST".equalsIgnoreCase(request.getMethod())){
			try {
				String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				logger.info("request method -> {} request uri -> {} request body -> {} ", request.getMethod(), request.getRequestURI(), test);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			logger.info("request method -> {} request uri -> {} ", request.getMethod(), request.getRequestURI());
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
}
