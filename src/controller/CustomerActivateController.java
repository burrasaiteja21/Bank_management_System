package controller;

import java.io.IOException;
import model.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employee/customer-activate")
public class CustomerActivateController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountNumber = req.getParameter("accountNumber");
        Status status = dao.CustomerActivateDao.execute(accountNumber);
        req.setAttribute("status", status);
        req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
    }
}
