package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeWithdrawDepositPortal
 * 
 * This servlet handles the request to access the employee's withdraw or deposit portal.
 * It forwards the request to the appropriate HTML page that allows employees to 
 * perform withdrawal or deposit actions.
 */
@WebServlet("/employee/withdraw-deposit-portal")
public class EmployeeWithdrawDepositPortal extends HttpServlet {

    /**
     * Handles the HTTP GET request to display the withdraw or deposit portal.
     * The request is forwarded to the EmployeeWithdrawOrDeposit.html page, where 
     * the employee can choose to withdraw or deposit funds.
     *
     * @param request  the HttpServletRequest object containing the request data
     * @param response the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /**
         * Forwarding the request to the EmployeeWithdrawOrDeposit.html page, where 
         * the employee can perform withdraw or deposit operations.
         */
        request.getRequestDispatcher("/WEB-INF/pages/html/EmployeeWithdrawOrDeposit.html").forward(request, response);
    }
}
