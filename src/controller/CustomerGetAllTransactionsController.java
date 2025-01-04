package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;
import model.Transaction;
import dao.ReadTransactionsByAccountNumberDbo;

/**
 * 
 * The CustomerGetAllTransactionsController handles requests for fetching all transactions
 * associated with a customer's account. It interacts with the DAO layer to retrieve the
 * transaction details and forwards the result to a JSP page for rendering.
 * 
 */
@WebServlet("/customer/view-alltransaction")
public class CustomerGetAllTransactionsController extends HttpServlet {

    /**
     * 
     * Handles HTTP POST requests to retrieve all transactions for a customer.
     * 
     * Steps:
     * 1. Retrieve the account number from the request attributes.
     * 2. Call the DAO layer to fetch the list of transactions for the account.
     * 3. Handle different cases:
     *    a. If the transactions list is `null`, forward an error message.
     *    b. If no transactions exist, forward a "no transactions" message.
     *    c. Otherwise, forward the list of transactions to the JSP for display.
     * 
     * @param request  HttpServletRequest object containing client request data.
     * @param response HttpServletResponse object for sending the response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     * 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 
         * Retrieve the account number from the request attributes.
         * 
         */
        String accountNumber = (String) request.getAttribute("accountNumber");

        /**
         * 
         * Fetch the list of transactions from the DAO layer.
         * 
         */
        ArrayList<Transaction> transactions = ReadTransactionsByAccountNumberDbo.execute(accountNumber);

        /**
         * 
         * Handle different cases based on the retrieved transactions:
         * 1. If `transactions` is `null`, set an error status and forward to status JSP.
         * 2. If the list is empty, set a "no transactions available" status and forward.
         * 3. Otherwise, set the list of transactions as a request attribute and forward to the appropriate JSP.
         * 
         */
        if (transactions == null) {
            /**
             * 
             * Handle the case where an error occurred while fetching transactions.
             * 
             */
            Status status = new Status("Some error occurred while fetching transactions, please try again.", true);
            request.setAttribute("status", status);
            request.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(request, response);
        } else {
            if (transactions.isEmpty()) {
                /**
                 * 
                 * Handle the case where no transactions are available.
                 * 
                 */
                Status status = new Status("No transactions available.", true);
                request.setAttribute("status", status);
                request.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(request, response);
            } else {
                /**
                 * 
                 * Handle the case where transactions are successfully retrieved.
                 * 
                 */
                request.setAttribute("transactions", transactions);
                request.getRequestDispatcher("/WEB-INF/pages/jsp/getTransactionsByAcc.jsp").forward(request, response);
            }
        }
    }
}
