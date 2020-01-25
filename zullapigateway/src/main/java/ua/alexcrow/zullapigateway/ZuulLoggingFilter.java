package ua.alexcrow.zullapigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("request method -> {} request uri -> {} ", request.getMethod(), request.getRequestURI());
		if ("POST".equalsIgnoreCase(request.getMethod())){
			try {
				String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				logger.info("request method -> {} request uri -> {} request body -> {} ", request.getMethod(), request.getRequestURI(), test);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
