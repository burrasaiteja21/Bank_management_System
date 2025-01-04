package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;
import dao.LoginCustomerDao;
import utils.CreateSession;

/**
 * Servlet implementation class LoginCustomerController
 * 
 * This servlet handles the customer login process. It provides both GET and POST 
 * methods to display the login page and validate the customer's credentials.
 */
@WebServlet("/login/customer")
public class LoginCustomerController extends HttpServlet {

    /**
     * This method handles the HTTP GET request for displaying the customer login page. 
     * When the user navigates to the "/login/customer" URL, it forwards the request 
     * to the customerLogin.html page where the customer can input their login credentials.
     * 
     * @param req  the HttpServletRequest object that contains the request data
     * @param resp the HttpServletResponse object that will contain the response
     * @throws ServletException if an error occurs during the request processing
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Forwarding the request to the customerLogin.html page.
         * This is the login form page where the user can enter their account number 
         * and password for authentication.
         */
        req.getRequestDispatcher("/WEB-INF/pages/html/customerLogin.html").forward(req, resp);
    }

    /**
     * This method handles the HTTP POST request for validating the customer login credentials. 
     * It retrieves the entered account number and password, hashes the password, and 
     * checks the credentials using the LoginCustomerDao. If the login is successful, 
     * it redirects the customer to the home page. If the login is invalid, 
     * it shows an error message.
     * 
     * @param req  the HttpServletRequest object that contains the login credentials
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if an error occurs during the request processing
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Retrieving the account number and password from the request.
         * The account number is used to identify the customer, while the password 
         * is needed for authentication.
         */
        String accountNumber = req.getParameter("accountNumber");
        String inputPassword = req.getParameter("password");

        try {
            /**
             * Hashing the input password using a utility method for security.
             * The password is hashed before being checked against the stored password 
             * to protect the customer's sensitive data.
             */
            String hashedInputPassword = utils.Generate.generateHash(inputPassword);
            System.out.println(hashedInputPassword);
            
            /**
             * Validating the login credentials by executing the LoginCustomerDao.
             * The credentials are checked against the database to see if they match.
             */
            boolean isValidLogin = LoginCustomerDao.execute(accountNumber, hashedInputPassword);

            if (isValidLogin) {
                
                /**
                 * If the login is successful, a session is created for the customer.
                 * The CreateSession utility class manages the session and stores 
                 * the customer's account number for later use.
                 */
                CreateSession.forCustomer(req, accountNumber);
                
                /**
                 * After successful login, the customer is redirected to their home page.
                 * This URL directs the customer to the "/customer/home" page, which
                 * displays the customer's dashboard.
                 */
                resp.sendRedirect("http://localhost:8080/bog/customer/home");
            } else {
                
                /**
                 * If the login fails, an error message is set as a status attribute.
                 * The message indicates that the credentials are invalid.
                 */
                req.setAttribute("status", new Status("invalid credentials", true));
                
                /**
                 * Forwarding the request to the status.jsp page where the error message
                 * will be displayed to the user.
                 */
                req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            
            /**
             * This block is a catch-all for exceptions. In this case, it is never expected
             * to be triggered, as any exception that occurs is handled internally in the 
             * utility methods and DAO.
             */
        }
    }
}
