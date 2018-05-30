package by.molchanov.humanresources.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Class {@link EncodingFilter} sets encoding to the entire web application which is able to deal with almost
 * every known written language.
 *
 * @author Molchanov Vladislav
 * @see Filter
 */
public class EncodingFilter implements Filter {

    private static final String ENCODING_PARAMETER_NAME = "encoding";
    private String encoding = "utf-8";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter(ENCODING_PARAMETER_NAME);
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

}
