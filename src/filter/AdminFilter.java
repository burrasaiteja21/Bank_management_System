package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/admin/*") 
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpSession existingSession = httpServletRequest.getSession(false);

        if (existingSession == null) {
            httpServletRequest.setAttribute("user", "admin");
            httpServletRequest.getRequestDispatcher("/WEB-INF/pages/jsp/invalidSession.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            String adminSession = (String) existingSession.getAttribute("admin");
            if (adminSession!=null && adminSession.equals("valid")) {
                chain.doFilter(request, response);
            } else {
                httpServletRequest.setAttribute("user", "admin");
                httpServletRequest.getRequestDispatcher("/WEB-INF/pages/jsp/invalidSession.jsp").forward(httpServletRequest, httpServletResponse);
            }
        }
    }
}
