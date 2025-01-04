package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import dao.CustomerUpdatePasswordDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;

/**
 * Servlet implementation class CustomerUpdatePasswordController
 * 
 * This servlet handles the process of updating a customer's password. 
 * It retrieves the current and new password from the customer, hashes them,
 * and then updates the password in the database. The result of the operation is
 * forwarded to the status.jsp page for display.
 */
@WebServlet("/customer/update-password")
public class CustomerUpdatePasswordController extends HttpServlet {
    
    /**
     * Handles the HTTP GET request. This method is invoked when the customer
     * navigates to the "/customer/update-password" URL. It forwards the request
     * to the customerChangePassword.html page, which allows the customer to 
     * input their current and new password.
     *
     * @param req  the HttpServletRequest object containing the request data
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Forwarding the request to the customerChangePassword.html page where the customer
         * can enter their current and new password.
         */
        req.getRequestDispatcher("/WEB-INF/pages/html/customerChangePassword.html").forward(req, resp);
    }

    /**
     * Handles the HTTP POST request to update the customer's password.
     * This method retrieves the customer's current password and new password,
     * hashes them, and calls the CustomerUpdatePasswordDao to update the password
     * in the database. The result of the operation is forwarded to the status.jsp page
     * for display.
     *
     * @param req  the HttpServletRequest object containing the request data
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Retrieving the customer's account number from the request attribute.
         * The account number is assumed to be set earlier in the request (e.g., during authentication).
         */
        String accountNumber = (String) req.getAttribute("accountNumber");
        
        /**
         * Retrieving the current and new passwords from the request parameters.
         * These passwords are input by the customer.
         */
        String currentpassword = null, newpassword = null;

        try {
            /**
             * Hashing the current and new passwords using the Generate utility class.
             * This ensures that the passwords are stored securely.
             */
            currentpassword = utils.Generate.generateHash(req.getParameter("currentPassword"));
            newpassword = utils.Generate.generateHash(req.getParameter("newPassword"));

            /**
             * Calling the CustomerUpdatePasswordDao to update the customer's password in the database.
             * The result of the operation is a Status object that contains a success or failure message.
             */
            Status res = CustomerUpdatePasswordDao.updatePassword(accountNumber, currentpassword, newpassword);

            /**
             * Setting the status object in the request attributes. This status will be forwarded
             * to the status.jsp page to inform the customer about the result of the password update.
             */
            req.setAttribute("status", res);

            /**
             * Forwarding the request to the status.jsp page to display the result of the password update.
             */
            req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);

        } catch (NoSuchAlgorithmException e) {
            /**
             * Catching the NoSuchAlgorithmException which might be thrown when hashing the password.
             * This exception is unlikely to occur, as the hashing algorithm is predefined.
             */
            // TODO Auto-generated catch block
            // This block is unlikely to be triggered since the hashing algorithm is predefined.
        }
    }
}
