package controller;

import java.io.IOException;
import dao.WithdrawDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;

/**
 * Servlet implementation class CustomerWithdrawController
 * 
 * This servlet handles the process of withdrawing funds from a customer's account.
 * It retrieves the account number and withdrawal amount from the request, 
 * processes the withdrawal using the WithdrawDao, and forwards the result 
 * to the status.jsp page for display.
 */
@WebServlet("/customer/withdraw")
public class CustomerWithdrawController extends HttpServlet {
    
    /**
     * Handles the HTTP POST request for a customer withdrawal.
     * This method retrieves the customer's account number and the requested withdrawal
     * amount from the request, processes the withdrawal through the WithdrawDao, 
     * and forwards the result (status of the withdrawal) to the status.jsp page.
     *
     * @param req  the HttpServletRequest object containing the request data
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Retrieving the account number from the request attribute.
         * The account number is assumed to be set earlier in the request (e.g., during authentication).
         */
        String accountNumber = (String) req.getAttribute("accountNumber");

        /**
         * Retrieving the withdrawal amount from the request parameters.
         * The amount is provided by the customer for the withdrawal.
         */
        Integer amount = Integer.parseInt(req.getParameter("amount"));

        /**
         * Calling the WithdrawDao to process the withdrawal. The status of the withdrawal
         * (whether successful or not) is returned as a Status object.
         */
        Status withDrawalRequestStatus = WithdrawDao.execute(accountNumber, amount);

        /**
         * Setting the status object in the request attributes. This status will be forwarded
         * to the status.jsp page to inform the customer about the result of the withdrawal operation.
         */
        req.setAttribute("status", withDrawalRequestStatus);

        /**
         * Forwarding the request to the status.jsp page to display the result of the withdrawal.
         */
        req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
    }
}
