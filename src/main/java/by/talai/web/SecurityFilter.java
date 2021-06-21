package by.talai.web;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        if (!"admin".equals(role) && !req.getRequestURI().endsWith("admin/*")) {
            req.getRequestDispatcher("/motor-depot/login").forward(request, response);
        }
        if (!"driver".equals(role)
                && !"dispatcher".equals(role)
                && !req.getRequestURI().endsWith("user/*")) {
            req.getRequestDispatcher(req.getContextPath() + "/login").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
