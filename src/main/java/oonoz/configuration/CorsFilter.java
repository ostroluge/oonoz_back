package oonoz.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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


/**
 * The Class CorsFilter.
 * 
 * Description :
 * 		Configuration class for Spring Security framework.
 * 
 * 		
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

  
	/** The driver class name. */
	@Value("${oonoz.front.origin}")
	private String allowedOrigin;
    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    	//Nested comment for SonarQube
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response=(HttpServletResponse) resp;
        HttpServletRequest request=(HttpServletRequest) req;
        String accessControlAllowHeaders = "Access-Control-Allow-Headers";
        response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader(accessControlAllowHeaders, "Authorization");
        response.addHeader(accessControlAllowHeaders, "Accept");
        response.addHeader(accessControlAllowHeaders,"Origin");
        response.addHeader(accessControlAllowHeaders,"X-Requested-With");
        response.addHeader(accessControlAllowHeaders,"Content-Type");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }

    }

    
    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    	//Nested comment for SonarQube
    }

}
