package fhtw.timetracker.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleCorsFIlter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
            ((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
            ((HttpServletResponse) response).setHeader("Access-Control-Max-Age", "3600");
            ((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            ((HttpServletResponse) response).setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            if ("OPTIONS".equals(((HttpServletRequest) request).getMethod())) {
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
