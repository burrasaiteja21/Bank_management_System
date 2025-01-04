package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dao.WithdrawDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Status;

/**
 * Servlet implementation class EmployeeWithdrawController
 * 
 * This servlet handles the withdrawal request for the employee. It processes
 * the withdrawal request by interacting with the WithdrawDao to perform the
 * transaction and then forwards the result to the status page.
 */
@WebServlet("/employee/withdraw")
public class EmployeeWithdrawController extends HttpServlet {

    /**
     * Handles the HTTP POST request to process a withdrawal.
     * It retrieves the account number and withdrawal amount from the request,
     * invokes the WithdrawDao to perform the withdrawal, and forwards the status
     * to the status.jsp page.
     *
     * @param req  the HttpServletRequest object containing the request data
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Retrieving the account number and withdrawal amount from the request parameters.
         * The account number is used to identify the account, and the amount specifies
         * the withdrawal amount.
         */
        String accountNumber = req.getParameter("accountNumber");
        Integer amount = Integer.parseInt(req.getParameter("amount"));
        
        /**
         * Calling the WithdrawDao to process the withdrawal request and obtain the status
         * of the withdrawal transaction.
         */
        Status withDrawalRequestStatus = WithdrawDao.execute(accountNumber, amount);
        
        /**
         * Setting the status of the withdrawal request as a request attribute to be displayed
         * on the status page.
         */
        req.setAttribute("status", withDrawalRequestStatus);
        
        /**
         * Forwarding the request to the status.jsp page where the withdrawal result is displayed
         * to the user.
         */
        req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
    }
}
