package by.molchanov.humanresources.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.COMMAND;
import static by.molchanov.humanresources.command.SessionRequestAttributeName.ROLE;

public class CommandRoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute(ROLE);
        String command = request.getParameter(COMMAND);
        CommandAffiliation commandAffiliation = CommandAffiliation.getInstance();
        if (commandAffiliation.isCommandCorrect(role, command)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/page/error_page.jsp");
        }
    }
}
