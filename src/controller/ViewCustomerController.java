package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ReadCustomerDao;
import model.Customer;
import model.Status;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class ViewCustomerController
 * 
 * This servlet handles the functionality of viewing customer details. It supports both GET and POST methods:
 * - The GET method displays the form to input the account number.
 * - The POST method processes the account number, fetches the customer details from the database,
 *   and displays the customer information or an error message if the customer is not found or an error occurs.
 */
@WebServlet("/employee/view-customer")
public class ViewCustomerController extends HttpServlet {

    /**
     * Handles the HTTP GET request.
     * This method displays the form for inputting the customer account number. It forwards the request to 
     * accountNumberInput.html where the employee can enter the account number.
     * 
     * @param req  the HttpServletRequest object that contains the request data
     * @param resp the HttpServletResponse object that will contain the response
     * @throws ServletException if an error occurs during the request processing
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Forward the request to the account number input form (accountNumberInput.html) 
         * for the employee to enter the customer account number.
         */
        req.getRequestDispatcher("/WEB-INF/pages/html/accountNumberInput.html").forward(req, resp);
    }

    /**
     * Handles the HTTP POST request.
     * This method processes the account number submitted by the employee, retrieves the customer information
     * from the database using ReadCustomerDao, and displays either the customer details or an error message.
     * 
     * @param req  the HttpServletRequest object that contains the customer account number
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if an error occurs during the request processing
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Retrieve the account number submitted by the employee from the form.
         */
        String accountNumber = req.getParameter("accountNumber");

        /**
         * Use the ReadCustomerDao to fetch the customer details based on the account number.
         * If the customer is found, their information is displayed; if not, an error message is shown.
         */
        Customer customer = ReadCustomerDao.execute(accountNumber);
        Status status = null;

        if (customer == null) {
            /**
             * If no customer is found, or if an error occurs while fetching customer data,
             * set the status with an error message and forward to the status.jsp page to display the message.
             */
            status = new Status("Some error occurred while fetching customer data. Please try again later.", true);
            req.setAttribute("status", status);
            req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
        } else {
            /**
             * If the customer account number is null (i.e., no customer found), 
             * set the status with a message indicating that the customer wasn't found.
             */
            if (customer.getAccountNumber() == null) {
                status = new Status("Customer not found", true);
                req.setAttribute("status", status);
                req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
            } else {
                /**
                 * If the customer is found, forward their details to the customerDetailsForm.jsp page.
                 * Set the account number and customer object as request attributes so the details can be displayed in the JSP.
                 */
                req.setAttribute("accountNumber", accountNumber);
                req.setAttribute("Customer", customer);

                /**
                 * Use a RequestDispatcher to forward the request to the customerDetailsForm.jsp page,
                 * where the customer information will be displayed.
                 */
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/jsp/customerDetailsForm.jsp");
                rd.forward(req, resp);
            }
        }
    }
}
