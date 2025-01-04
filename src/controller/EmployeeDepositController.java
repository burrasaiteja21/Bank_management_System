package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dao.DepositDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;

/**
 * Servlet implementation class EmployeeDepositController
 * 
 * This servlet handles the process of depositing funds into a customer's account.
 * It retrieves the account number and deposit amount from the request, processes 
 * the deposit using the DepositDao, and forwards the result (status of the deposit) 
 * to the status.jsp page for display.
 */
@WebServlet("/employee/deposit")
public class EmployeeDepositController extends HttpServlet {

    /**
     * Handles the HTTP POST request for depositing funds into a customer's account.
     * This method retrieves the account number and deposit amount from the request parameters, 
     * processes the deposit through the DepositDao, and forwards the result (status of the deposit)
     * to the status.jsp page for display.
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
         * This account number specifies the customer's account into which the deposit will be made.
         */
        String accountNumber = req.getParameter("accountNumber");
        
        /**
         * Retrieving the deposit amount from the request parameters.
         * This amount is the value to be deposited into the customer's account.
         */
        Integer amount = Integer.parseInt(req.getParameter("amount"));

        /**
         * Calling the DepositDao to process the deposit. The status of the deposit 
         * (whether successful or not) is returned as a Status object.
         */
        Status depositRequestStatus = DepositDao.execute(accountNumber, amount);

        /**
         * Setting the status object in the request attributes. This status will be forwarded 
         * to the status.jsp page to inform the user about the result of the deposit operation.
         */
        req.setAttribute("status", depositRequestStatus);

        /**
         * Forwarding the request to the status.jsp page to display the result of the deposit.
         */
        req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
    }
}
