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

@WebFilter("/employee/*") /*
    *Applies to all URL patterns under "/employee/*"
 */ 
public class EmployeeFilter implements Filter {

    /* 
     * This method is responsible for filtering requests to URLs under "/employee/*".
     * It checks if the user has a valid session; if not, they are redirected to an error page.
     * If the session exists, it checks for the employee ID and forwards the request to the next filter or resource.
     *
     * @param request: The ServletRequest object, which holds request data.
     * @param response: The ServletResponse object, which handles the response.
     * @param chain: The FilterChain object, used to pass the request and response to the next filter or resource.
     * @throws IOException: If an error occurs while handling I/O during the request/response process.
     * @throws ServletException: If an error occurs during the request handling.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        /* 
         * Cast the ServletRequest and ServletResponse objects to HttpServletRequest 
         * and HttpServletResponse to access HTTP-specific features like session handling.
         */
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        /* 
         * Attempt to retrieve the existing session. If no session exists, return null.
         */
        HttpSession existingSession = httpServletRequest.getSession(false);

        /* 
         * If no session exists, set the "user" attribute to indicate an invalid session 
         * and forward the request to "invalidSession.jsp" for displaying an error message.
         */
        if (existingSession == null) {
            httpServletRequest.setAttribute("user", "employee");
            httpServletRequest.getRequestDispatcher("/WEB-INF/pages/jsp/invalidSession.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            /* 
             * If the session exists, retrieve the employee ID from the session attributes.
             * If the employee ID is present, set it as a request attribute and continue with the request processing.
             */
            String employeeId = (String) existingSession.getAttribute("employeeId");
            if (employeeId != null) {
                request.setAttribute("employeeId", employeeId);
                chain.doFilter(request, response);
            } else {
                /* 
                 * If the employee ID is not found in the session, set the "user" attribute to indicate an invalid session 
                 * and forward the request to "invalidSession.jsp" for displaying an error message.
                 */
                httpServletRequest.setAttribute("user", "employee");
                httpServletRequest.getRequestDispatcher("/WEB-INF/pages/jsp/invalidSession.jsp").forward(httpServletRequest, httpServletResponse);
            }
        }
    }
}
