package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerWithdrawDepositPortal
 * 
 * This servlet handles the process of displaying the withdraw or deposit portal page for the customer.
 * It forwards the request to the CustomerWithdrawOrDeposit.html page, where the customer can choose 
 * whether to withdraw or deposit funds into their account.
 */
@WebServlet("/customer/withdraw-deposit-portal")
public class CustomerWithdrawDepositPortal extends HttpServlet {
    
    /**
     * Handles the HTTP GET request. This method is invoked when the customer
     * navigates to the "/customer/withdraw-deposit-portal" URL. It forwards the 
     * request to the CustomerWithdrawOrDeposit.html page, where the customer 
     * can choose to withdraw or deposit funds.
     *
     * @param request  the HttpServletRequest object containing the request data
     * @param response the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Forwarding the request to the CustomerWithdrawOrDeposit.html page.
         * This page will provide the interface for the customer to choose
         * between withdrawing or depositing funds.
         */
        request.getRequestDispatcher("/WEB-INF/pages/html/CustomerWithdrawOrDeposit.html").forward(request, response);
    }
}
