package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;
import dao.LoginEmployeeDao;
import utils.CreateSession;

/**
 * Servlet implementation class LoginEmployeeController
 * 
 * This servlet handles the employee login process. It supports both GET and POST methods:
 * - The GET method displays the login form.
 * - The POST method validates the employee's credentials and establishes a session if valid.
 */
@WebServlet("/login/employee")
public class LoginEmployeeController extends HttpServlet {

    /**
     * This method handles the HTTP GET request for displaying the employee login page.
     * When an employee accesses the "/login/employee" URL, the login form (employeeLogin.html) is shown.
     * 
     * @param req  the HttpServletRequest object that contains the request data
     * @param resp the HttpServletResponse object that will contain the response
     * @throws ServletException if an error occurs during the request processing
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Forward the request to the employee login page (employeeLogin.html).
         * This page includes fields for the employee to enter their login credentials.
         */
        req.getRequestDispatcher("/WEB-INF/pages/html/employeeLogin.html").forward(req, resp);
    }

    /**
     * This method handles the HTTP POST request for validating the employee's login credentials.
     * It retrieves the entered employee ID and password, hashes the password, and checks the credentials
     * against the database using the LoginEmployeeDao. If the login is successful, a session is created 
     * for the employee. If the credentials are invalid, an error message is displayed.
     * 
     * @param req  the HttpServletRequest object that contains the login credentials
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if an error occurs during the request processing
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Retrieve the employee's ID and the entered password from the request.
         * The ID is used to identify the employee, and the password is used for authentication.
         */
        String id = req.getParameter("id");
        String inputPassword = req.getParameter("password");
        System.out.println(inputPassword);

        try {
            
            /**
             * Hash the entered password to ensure it is securely stored and compared to the hashed password
             * in the database. This is a security measure to protect the employee's sensitive information.
             */
            String hashedInputPassword = utils.Generate.generateHash(inputPassword);
            System.out.print(hashedInputPassword);
            
            /**
             * Validate the employee's credentials by executing the LoginEmployeeDao.
             * The credentials are checked against the database to see if they match.
             */
            boolean isValidLogin = LoginEmployeeDao.execute(id, hashedInputPassword);

            if (isValidLogin) {
                
                /**
                 * If the login is successful, create a session for the employee.
                 * The CreateSession utility class handles storing the employee's ID for use during the session.
                 */
                CreateSession.forEmployee(req, id);
                /**
                 * Redirect the employee to the home page (employee/home) after a successful login.
                 * This is the dashboard page where the employee can perform various actions.
                 */
                resp.sendRedirect("http://localhost:8080/bog/employee/home");
            } else {
                
                /**
                 * If the login fails (invalid credentials), set the status message indicating an error.
                 * The status message is forwarded to the status.jsp page for display to the user.
                 */
                Status status = new Status("invalid credentials", true);
                req.setAttribute("status", status);
                req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            
            /**
             * Catch any unexpected exceptions. In this case, it is assumed that this block will never be triggered
             * because the utility methods and DAO should handle any errors appropriately.
             */
        }
    }
}
