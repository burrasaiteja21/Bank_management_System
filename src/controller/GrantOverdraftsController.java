package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dao.GrantOverdraftsDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;

/**
 * Servlet implementation class GrantOverdraftsController
 * 
 * This servlet handles both GET and POST requests related to granting overdrafts to
 * customers. It allows an employee to grant an overdraft to a customer account by
 * forwarding the request to a form for overdraft entry and processing the request
 * to update the account's overdraft limit.
 */
@WebServlet("/employee/grantoverdrafts")
public class GrantOverdraftsController extends HttpServlet {

    /**
     * Handles the HTTP GET request by forwarding the request to the overdraft
     * granting form (grantoverdraft.html) where the employee can enter the
     * account number and overdraft limit.
     *
     * @param req  the HttpServletRequest object containing the request data
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Forwarding the request to the grantoverdraft.html page where the employee
         * can input the account number and the overdraft amount to be granted.
         */
        req.getRequestDispatcher("/WEB-INF/pages/html/grantoverdraft.html").forward(req, resp);
    }

    /**
     * Handles the HTTP POST request by retrieving the account number and overdraft
     * amount from the request, calling the GrantOverdraftsDao to update the account,
     * and forwarding the result to the status.jsp page to display the status of
     * the overdraft request.
     *
     * @param request  the HttpServletRequest object containing the request data
     * @param response the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /**
         * Retrieving the account number and overdraft amount from the request parameters.
         * The account number identifies the customer account and the overdraft is the 
         * amount to be granted to that account.
         */
        String accountNumber = request.getParameter("accountNumber");
        int overdraft = Integer.parseInt(request.getParameter("overdraft"));
        
        /**
         * Calling the GrantOverdraftsDao to execute the overdraft granting process.
         * The result status is captured in the 'status' object, which is then forwarded
         * to the status.jsp page to display the success or failure message to the employee.
         */
        Status status = GrantOverdraftsDao.execute(accountNumber, overdraft);
        
        /**
         * Setting the status of the overdraft grant operation as a request attribute 
         * and forwarding the request to the status.jsp page to show the result to the employee.
         */
        request.setAttribute("status", status);
        request.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(request, response);
    }
}
