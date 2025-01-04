package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerHomeController
 * 
 * This servlet handles requests for the customer's home page and forwards them
 * to the corresponding HTML page. It is responsible for displaying the home page
 * when the customer navigates to the /customer/home URL.
 */
@WebServlet("/customer/home")
public class CustomerHomeController extends HttpServlet {
    
    /**
     * Handles the HTTP GET request. This method is invoked when the customer
     * navigates to the "/customer/home" URL. It forwards the request to the 
     * customerHome.html page, which is displayed to the customer.
     *
     * @param request  the HttpServletRequest object containing the request data
     * @param response the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Forwarding the request to the customer home page (HTML).
         * The customerHome.html page will be rendered in response to the customer's request.
         */
        request.getRequestDispatcher("/WEB-INF/pages/html/customerHome.html").forward(request, response);
    }
}
