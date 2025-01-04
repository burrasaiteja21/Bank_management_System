package controller;

import java.io.IOException;

import dao.ReadTransactionByIdDbo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;
import model.Transaction;

/**
 * 
 * The CustomerGetTransactionByIdController handles requests to fetch a transaction
 * by its unique identifier. It ensures that the customer has access rights to view
 * the transaction and forwards the appropriate response.
 * 
 */
@WebServlet("/customer/get-transaction-by-id")
public class CustomerGetTransactionByIdController extends HttpServlet {

    /**
     * 
     * Handles HTTP POST requests to retrieve a transaction by its ID.
     * 
     * Steps:
     * 1. Retrieve the transaction ID from the request parameters.
     * 2. Use the DAO layer to fetch the transaction details.
     * 3. Handle different cases:
     *    a. If the transaction is null, forward an error message.
     *    b. If the transaction ID is null, indicate that the transaction does not exist.
     *    c. If the customer is authorized to access the transaction, display the transaction details.
     *    d. If unauthorized access is detected, forward an "Access restricted" message.
     * 
     * @param req  HttpServletRequest object containing client request data.
     * @param resp HttpServletResponse object for sending the response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     * 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 
         * Retrieve the transaction ID from the request parameters.
         * 
         */
        String transactionId = req.getParameter("id");

        /**
         * 
         * Fetch the transaction details from the DAO layer.
         * 
         */
        Transaction transaction = ReadTransactionByIdDbo.execute(transactionId);

        /**
         * 
         * Handle different cases for the retrieved transaction:
         * 1. If `transaction` is null, set an error status and forward to status JSP.
         * 2. If the transaction ID is null, indicate that no such transaction exists.
         * 3. If the customer is authorized, display the transaction details.
         * 4. If unauthorized access is detected, forward an "Access restricted" message.
         * 
         */
        if (transaction == null) {
            /**
             * 
             * Handle case where an error occurred while fetching the transaction.
             * 
             */
            Status status = new Status("Some error occurred while fetching the transaction, please try again.", true);
            req.setAttribute("status", status);
            req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
        } else {
            if (transaction.getTransactionId() == null) {
                /**
                 * 
                 * Handle case where no transaction exists with the given ID.
                 * 
                 */
                Status status = new Status("No such transaction exists.", true);
                req.setAttribute("status", status);
                req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
            } else {
                /**
                 * 
                 * Check customer access rights to the transaction.
                 * 
                 */
                String fromAccount = transaction.getFromAccount();
                String toAccount = transaction.getToAccount();
                String customerAccount = (String) req.getAttribute("accountNumber");

                if ((fromAccount != null && fromAccount.equals(customerAccount)) || 
                    (toAccount != null && toAccount.equals(customerAccount))) {
                    /**
                     * 
                     * Customer is authorized to view the transaction.
                     * 
                     */
                    req.setAttribute("transaction", transaction);
                    req.getRequestDispatcher("/WEB-INF/pages/jsp/viewTransactionById.jsp").forward(req, resp);
                } else {
                    /**
                     * 
                     * Handle case where access is restricted for the customer.
                     * 
                     */
                    Status status = new Status("Access restricted.", true);
                    req.setAttribute("status", status);
                    req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
                }
            }
        }
    }
}
