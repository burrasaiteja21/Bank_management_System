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

@WebFilter("/customer/*") /*
    *Applies to all URL patterns under "/customer/*"
*/
public class CustomerFilter implements Filter {

    /* 
     * This method is responsible for filtering incoming requests to the '/customer/*' URLs.
     * It checks if the user has a valid session, and if not, redirects them to an error page.
     * If the session exists, it checks for the account number and forwards the request to the next filter or resource.
     *
     * @param request: The ServletRequest object, which encapsulates the request data.
     * @param response: The ServletResponse object, which is used to send the response.
     * @param chain: The FilterChain object, used to pass the request and response to the next filter or resource.
     * @throws IOException: In case of an I/O error during the request/response handling.
     * @throws ServletException: If an error occurs during request handling.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        /* 
         * Cast the ServletRequest and ServletResponse objects to HttpServletRequest 
         * and HttpServletResponse for accessing HTTP-specific features like session management.
         */
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        /* 
         * Retrieve the existing session, if it exists.
         * If no session is found, null is returned.
         */
        HttpSession existingSession = httpServletRequest.getSession(false);

        /* 
         * If no session exists, set an attribute to indicate an invalid session and forward the user 
         * to the "invalidSession.jsp" page to display an error message.
         */
        if (existingSession == null) {
            httpServletRequest.setAttribute("user", "customer");
            httpServletRequest.getRequestDispatcher("/WEB-INF/pages/jsp/invalidSession.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            /* 
             * If a session exists, retrieve the account number from the session attributes.
             * If the account number is found, set it as a request attribute and continue the request handling by passing 
             * the request and response to the next filter or resource in the chain.
             */
            String accountNumber = (String) existingSession.getAttribute("accountNumber");
            if (accountNumber != null) {
                request.setAttribute("accountNumber", accountNumber);
                chain.doFilter(request, response);
            } else {
                /* 
                 * If the account number is not found in the session, set the "user" attribute to indicate an invalid session
                 * and forward the request to the "invalidSession.jsp" page for displaying an error message.
                 */
                httpServletRequest.setAttribute("user", "customer");
                httpServletRequest.getRequestDispatcher("/WEB-INF/pages/jsp/invalidSession.jsp").forward(httpServletRequest, httpServletResponse);
            }
        }
    }
}
