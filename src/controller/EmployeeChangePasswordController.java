package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import dao.EmployeeChangePasswordDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;

@WebServlet("/employee/update-password")
public class EmployeeChangePasswordController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/html/employeeChangePassword.html").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = (String)req.getAttribute("employeeId");
        String hashedOldPassword=null;
        String hashedNewPassword=null;
        try {
            hashedOldPassword = utils.Generate.generateHash(req.getParameter("oldPassword"));
            hashedNewPassword = utils.Generate.generateHash(req.getParameter("newPassword"));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Status status = EmployeeChangePasswordDao.execute(employeeId, hashedOldPassword, hashedNewPassword);
        req.setAttribute("status", status);
        req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
    }
}
