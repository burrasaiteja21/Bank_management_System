package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Status;

@WebServlet("/login/admin")
public class LoginAdminController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/html/adminLogin.html").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminId = request.getParameter("adminId");
		try {
			String hashedPasswordInput = utils.Generate.generateHash(request.getParameter("password"));
			System.out.println(hashedPasswordInput);
			boolean validAdminLogin = dao.AdminLoginDao.execute(adminId, hashedPasswordInput);
			if(validAdminLogin){
				utils.CreateSession.forAdmin(request);
				response.sendRedirect("/bog/admin/home");
			}
			else{
				Status status = new Status("Invalid admin credentials",true);
				request.setAttribute("status", status);
				request.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(request, response);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			//never occuring case
			e.printStackTrace();
		}
		
	}

}
