package controller;

import dao.DatabaseConnection;
import dao.ReadTransactionByIdDbo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;
import model.Transaction;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class EmployeeGetTransactionByIdController
 * 
 * This servlet handles the process of fetching transaction details by transaction ID.
 * It retrieves the transaction ID from the request, queries the database for the transaction,
 * and displays either the transaction details or an error message to the user.
 */
@WebServlet("/employee/get-transaction-by-id")
public class EmployeeGetTransactionByIdController extends HttpServlet {
    
    /**
     * Handles the HTTP POST request to fetch a transaction by its ID.
     * This method retrieves the transaction ID from the request, queries the database using
     * the ReadTransactionByIdDbo, and forwards the result (transaction details or error message)
     * to the appropriate JSP page for display.
     *
     * @param request  the HttpServletRequest object containing the request data
     * @param response the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /**
         * Retrieving the transaction ID from the request parameters.
         * This ID is used to query the database for the corresponding transaction details.
         */
        String transactionId = request.getParameter("id");

        /**
         * Querying the database for the transaction details using the provided transaction ID.
         * The ReadTransactionByIdDbo executes the query and returns the corresponding Transaction object.
         */
        Transaction transaction = ReadTransactionByIdDbo.execute(transactionId);

        /**
         * Checking if the transaction object is null, indicating an error during the transaction fetch process.
         * If the transaction is null, an error status message is set and forwarded to the status.jsp page.
         */
        if (transaction == null) {
            request.setAttribute("status", new Status("Some error occurred while fetching transactions. Please try again.", true));
            request.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(request, response);
        } else {
            
            /**
             * Checking if the transaction ID is null within the retrieved transaction object.
             * If the transaction ID is null, it means no transaction was found for the provided ID.
             * In this case, a "No such transaction" status is set and forwarded to the status.jsp page.
             */
            if (transaction.getTransactionId() == null) {
                request.setAttribute("status", new Status("No such transaction", true));
                request.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(request, response);
            } else {
                
                /**
                 * If a valid transaction is found, the transaction details are set in the request attribute.
                 * The request is then forwarded to the viewTransactionById.jsp page to display the transaction details.
                 */
                request.setAttribute("transaction", transaction);
                request.getRequestDispatcher("/WEB-INF/pages/jsp/viewTransactionById.jsp").forward(request, response);
            }
        }
    }
}
