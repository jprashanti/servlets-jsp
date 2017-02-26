package src.main.java.com.bellinf.batch4.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.RepositoryIdHelper;




public class RegisterServlet extends HttpServlet{
	private String admin_email =null;
	private String city =null;
	public void init(){
		System.out.println("RegisterServlet Loaded");
		ServletConfig sc = getServletConfig();
		ServletContext sctx = sc.getServletContext();
		city= getServletConfig().getInitParameter("city");
		admin_email =sctx.getInitParameter("admin-email");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		String name = req.getParameter("fullname");
		String email = req.getParameter("email");
		String loginId =req.getParameter("username");
		String pass = req.getParameter("password");
		String validatePass = req.getParameter("cofirm-password");
	
		String validation = null;
		
		if(!pass.equals(validatePass)){
			validation = "passwords doesn't match";
		}
		
		if(validation !=null){
			req.setAttribute("validationInfo", validation);
		} else {
			RegistrationDetails regDetails = new RegistrationDetails();
			regDetails.setFullname(name);
			regDetails.setEmail(email);
			regDetails.setUsername(loginId);
			regDetails.setPassword(pass);
			
			RespositoryDAO repDao = new RespositoryDAO();
            int result = repDao.saveRegistraionDetails(regDetails);
            if(result == 1){
            	req.setAttribute("validationInfo", "Successful");
            	req.setAttribute("CITY", city);
            	req.setAttribute("ADMIN-DETAILS", admin_email);
            } else {
            	req.setAttribute("validationInfo", "Failed to register with info provided, please try later or contact below admin email");
            	req.setAttribute("CITY", city);
            	req.setAttribute("ADMIN-DETAILS", admin_email);
            }
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("RegistrationStatus.jsp");
		rd.forward(req, resp);
		
		
	}
	

}
