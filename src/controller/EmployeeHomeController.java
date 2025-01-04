package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeHomeController
 * 
 * This servlet handles the request for the employee's home page. It forwards the
 * request to the appropriate HTML page to display the employee home page.
 */
@WebServlet("/employee/home")
public class EmployeeHomeController extends HttpServlet {

    /**
     * Handles the HTTP GET request for the employee home page.
     * This method forwards the request to the employeeHome.html page.
     *
     * @param req  the HttpServletRequest object containing the request data
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Forwarding the request to the employee home page (HTML).
         * The employeeHome.html page will be rendered for the user.
         */
        req.getRequestDispatcher("/WEB-INF/pages/html/employeeHome.html").forward(req, resp);
    }
}
