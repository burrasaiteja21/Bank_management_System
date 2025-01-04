package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateSession {

    /*
     * This method creates a session for an employee. If there is an existing session, it will be invalidated.
     * A new session is then created and the employeeId is stored in the session as an attribute.
     * The session is set to expire after 5 minutes of inactivity.
     * 
     * @param request The HttpServletRequest object used to manage the session.
     * @param employeeId The unique ID of the employee to be stored in the session.
     * @return The session ID of the newly created session.
     */
    public static void forEmployee(HttpServletRequest request, String employeeId) {
        HttpSession session = request.getSession(false); 
        if(session != null) {
            session.invalidate(); 
        }

        session = request.getSession(); 
        session.setAttribute("employeeId", employeeId); 
        session.setMaxInactiveInterval(5 * 60); 
        
    }

    /*
     * This method creates a session for a customer. If there is an existing session, it will be invalidated.
     * A new session is created, and the accountNumber is stored in the session as an attribute.
     * The session is set to expire after 5 minutes of inactivity.
     * 
     * @param request The HttpServletRequest object used to manage the session.
     * @param accountNumber The account number of the customer to be stored in the session.
     * @return The session ID of the newly created session.
     */
    public static void forCustomer(HttpServletRequest request, String accountNumber) {
        HttpSession session = request.getSession(false); 
        if(session != null) {
            session.invalidate(); 
        }
        session = request.getSession(); 
        session.setAttribute("accountNumber", accountNumber); 
        session.setMaxInactiveInterval(5 * 60); 
    }
    
    public static void forAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false); 
        if(session != null) {
            session.invalidate(); 
        }
        session = request.getSession(); 
        session.setAttribute("admin", "valid"); 
        session.setMaxInactiveInterval(5 * 60);
    }
}
