package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dao.ReadTransactionsByAccountNumberDbo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;
import model.Transaction;

/**
 * Servlet implementation class EmployeeGetTransactionsByAccountNumberController
 * 
 * This servlet handles the process of fetching transaction details by account number.
 * It retrieves the account number from the request, queries the database for all transactions
 * related to the specified account, and then forwards the result to the appropriate JSP page 
 * for display or an error message if no transactions are found.
 */
@WebServlet("/employee/get-transactions-by-account")
public class EmployeeGetTransactionsByAccountNumberController extends HttpServlet {
    
    /**
     * Handles the HTTP POST request to fetch transactions by account number.
     * This method retrieves the account number from the request, queries the database using
     * the ReadTransactionsByAccountNumberDbo, and forwards the result (transaction list or error message)
     * to the appropriate JSP page for display.
     *
     * @param req  the HttpServletRequest object containing the request data
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Retrieving the account number from the request parameters.
         * This account number will be used to query the database for the transactions related to the account.
         */
        String accountNumber = req.getParameter("accountNumber");

        /**
         * Querying the database to fetch all transactions associated with the provided account number.
         * The transactions are returned as an ArrayList of Transaction objects.
         */
        ArrayList<Transaction> transactions = ReadTransactionsByAccountNumberDbo.execute(accountNumber);

        /**
         * Checking if the transactions list is null, indicating an error occurred while fetching transactions.
         * If null, an error message is set and the request is forwarded to the status.jsp page for display.
         */
        if (transactions == null) {
            req.setAttribute("status", new Status("Some error occurred while fetching transactions. Please try again.", true));
            req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
        } else {
            
            /**
             * Checking if the transactions list is empty, indicating that no transactions were found for the given account.
             * If empty, a "No transactions available" message is set and the request is forwarded to the status.jsp page for display.
             */
            if (transactions.size() == 0) {
                req.setAttribute("status", new Status("No transactions available", true));
                req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
            } else {
                
                /**
                 * If transactions are found, the list of transactions is set in the request attribute.
                 * The request is then forwarded to the getTransactionsByAcc.jsp page for displaying the transaction details.
                 */
                req.setAttribute("transactions", transactions);
                req.getRequestDispatcher("/WEB-INF/pages/jsp/getTransactionsByAcc.jsp").forward(req, resp);
            }
        }
    }
}
