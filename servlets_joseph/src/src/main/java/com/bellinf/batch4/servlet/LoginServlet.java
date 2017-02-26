package src.main.java.com.bellinf.batch4.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginServlet extends HttpServlet{
	
	
	public void init(){
		System.out.println("LoginServlet Loaded");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String loginId = req.getParameter("userName");
		String password = req.getParameter("password");
		String message = null;
		
		RespositoryDAO repDAO = new RespositoryDAO();
		boolean result = repDAO.getUserDetails(loginId, password);
		if(result){
			message = "Login Successful, Welcome to BellBatch 4";
		} else {
			message ="Username & Password invalid. Please try again";
		}
		
		req.setAttribute("info", message);
		
		RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
		rd.forward(req, resp);
	}
	
	

}
