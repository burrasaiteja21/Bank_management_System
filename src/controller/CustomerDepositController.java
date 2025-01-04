package controller;

import java.io.IOException;

import dao.DepositDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;

/**
 * 
 * The CustomerDepositController handles requests for depositing money into a customer's account.
 * It retrieves the account details and deposit amount, processes the transaction through the DAO layer,
 * and forwards the status of the transaction to a JSP page for display.
 * 
 */
@WebServlet("/customer/deposit")
public class CustomerDepositController extends HttpServlet {

    /**
     * 
     * Handles HTTP POST requests for customer deposit transactions.
     * 
     * Steps:
     * 1. Retrieve the account number from the request attributes.
     * 2. Parse the deposit amount from the request parameters.
     * 3. Call the DAO layer to process the deposit transaction.
     * 4. Set the transaction status as a request attribute.
     * 5. Forward the request to the status JSP for rendering.
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
         * Retrieve the account number from the request attributes.
         * 
         */
        String accountNumber = (String) req.getAttribute("accountNumber");

        /**
         * 
         * Parse the deposit amount from the request parameters.
         * 
         */
        Integer amount = Integer.parseInt(req.getParameter("amount"));

        /**
         * 
         * Execute the deposit transaction using the DAO layer and obtain the status.
         * 
         */
        Status depositRequestStatus = DepositDao.execute(accountNumber, amount);

        /**
         * 
         * Set the transaction status as a request attribute.
         * 
         */
        req.setAttribute("status", depositRequestStatus);

        /**
         * 
         * Forward the request to the status JSP for rendering.
         * 
         */
        req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
    }
}
