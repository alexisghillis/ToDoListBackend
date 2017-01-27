package net.metrosystems.filters;

/**
 * Created by alexis-toma.ghillis on 1/26/2017.
 */

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A resource makes a cross-origin HTTP request when it requests a resource from a different domain than the one which the first resource itself serves.
 * For security reasons, browsers restrict cross-origin HTTP requests initiated from within scripts.
 * <p>
 * The Cross-Origin Resource Sharing standard works by adding new HTTP headers that allow servers to describe the set of origins that are permitted to read that information using a web browser.
 * The specification mandates that browsers "preflight" the request, soliciting supported methods from the server with an HTTP OPTIONS request method,
 * and then, upon "approval" from the server, sending the actual request with the actual HTTP request method.
 * <p>
 * You can set CORS using spring boot default configurations, as shown below.
 * <p>
 * # ENDPOINTS CORS CONFIGURATION (EndpointCorsProperties)
 * endpoints.cors.allow-credentials=# Set whether credentials are supported. When not set, credentials are not supported.
 * endpoints.cors.allowed-headers=# Comma-separated list of headers to allow in a request.
 * endpoints.cors.allowed-methods=GET # Comma-separated list of methods to allow. '*' allows all methods.
 * endpoints.cors.allowed-origins=# Comma-separated list of origins to allow. '*' allows all origins. When not set, CORS support is disabled.
 * endpoints.cors.exposed-headers=# Comma-separated list of headers to include in a response.
 * endpoints.cors.max-age=1800 # How long, in seconds, the response from a pre-flight request can be cached by clients.
 */

@Component
public class CrossOriginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        // allow all origins
        response.setHeader("Access-Control-Allow-Origin", "*");

        // allow all methods
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, OPTIONS, DELETE, HEAD");

        // maximum duration between the preflight request and the actual request
        response.setHeader("Access-Control-Max-Age", "3600");

        // allowed headers to be used with the actual request
        // unfortunately using * here is not possible, so all the allowed headers have to be enumerated
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");

        chain.doFilter(req, res);

        // returning from the controller code here
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}
