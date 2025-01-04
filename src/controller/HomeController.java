package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 * 
 * This servlet handles the GET request for the home page. It forwards the request
 * to the home.html page, which contains the content to be displayed to the user.
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {

    /**
     * Handles the HTTP GET request. When the user navigates to the "/home" URL,
     * the request is forwarded to the home.html page.
     *
     * @param req  the HttpServletRequest object containing the request data
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Forwarding the request to the home.html page located in the WEB-INF/pages/html directory.
         * This page is the homepage that will be displayed to the user.
         */
        req.getRequestDispatcher("/WEB-INF/pages/html/home.html").forward(req, resp);
    }
}
