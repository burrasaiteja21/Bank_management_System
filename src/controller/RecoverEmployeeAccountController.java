package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;

@WebServlet("/recovery/employee")
public class RecoverEmployeeAccountController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/html/recoverEmployeeAccount.html").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = (String) req.getParameter("employeeId");
        String  newpassword = null,recoveryPhrase=null;
        try {
            newpassword = utils.Generate.generateHash(req.getParameter("newPassword"));
            recoveryPhrase = utils.Generate.generateHash(req.getParameter("recoveryPhrase"));
            Status res = dao.RecoverEmployeeAccountDao.execute(employeeId, newpassword,recoveryPhrase);
            req.setAttribute("status", res);
            req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);

        } catch (NoSuchAlgorithmException e) {
            //never occuring case
        }
    }
}