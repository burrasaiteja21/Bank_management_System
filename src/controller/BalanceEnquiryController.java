package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountOperationsDao;

/**
 * 
 * The BalanceEnquiryController handles balance enquiry requests for customers.
 * It retrieves account details from the DAO layer and forwards the data
 * to a JSP page for display.
 * 
 */
@WebServlet("/customer/balance")
public class BalanceEnquiryController extends HttpServlet {

    /**
     * 
     * Handles HTTP GET requests for balance enquiry.
     * 
     * Steps:
     * 1. Retrieve the account number from the request attributes.
     * 2. Fetch the account details from the AccountOperationsDao.
     * 3. Set the account details as a request attribute.
     * 4. Forward the request to the appropriate JSP for rendering.
     * 
     * @param request  HttpServletRequest object containing client request data.
     * @param response HttpServletResponse object for sending the response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     * 
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 
         * Retrieve the account number from the request attributes.
         * 
         */
        String accountNumber = (String) request.getAttribute("accountNumber");

        /**
         * 
         * Fetch account details as a HashMap from the DAO layer.
         * 
         */
        HashMap accountDetailHashMap = AccountOperationsDao.getAccountAttributes(accountNumber);

        /**
         * 
         * Add the account details to the request attributes.
         * 
         */
        request.setAttribute("accountDetailsHashMap", accountDetailHashMap);

        /**
         * 
         * Forward the request to the balance enquiry JSP page.
         * 
         */
        request.getRequestDispatcher("/WEB-INF/pages/jsp/balanceEnquiry.jsp").forward(request, response);
    }
}
