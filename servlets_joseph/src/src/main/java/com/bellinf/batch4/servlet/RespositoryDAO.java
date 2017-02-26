package src.main.java.com.bellinf.batch4.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class RespositoryDAO {

	Connection con = null;
	public static final String CREATE_TABLE = "Create table register(fullname character varying(40) NOT NULL, email character varying(40) NOT NULL, username character varying(40) NOT NULL, password character varying(40) NOT NULL)";
	public static final String INSERT_REGISTER = "INSERT INTO register (fullname, email, username, password) values(?,?,?,?)";
	public static final String SELECT_USER_INFO = "select * from register where username=? AND password=?";
	public static final String UPDATE_EMAIL = "UPDATE register set email=? where username=?";
	
	public void getConnection(){
		try {
		System.out.println("Before Postgresql Driver Registered");
		//Load the drive class
		Class.forName("org.postgresql.Driver");
		System.out.println("Postgresql Driver Registered");
		//Establish the connection
		con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/BellinfoBatch4", "postgres", "abcd12345");
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean createRegistration(){
		boolean result = false;
		Statement stmt = null;
		Statement stmt2 = null;
		boolean isTableExsist=false;
		try{
			getConnection();
			stmt2 = con.createStatement();
			stmt = con.createStatement();
			ResultSet rs = stmt2.executeQuery("SELECT EXISTS ( SELECT 1  FROM   pg_tables WHERE  schemaname = 'public' AND    tablename = 'register')");
			while(rs.next()){
				isTableExsist = rs.getBoolean(1);
			}			
			if(!isTableExsist){
				
				result = stmt.execute(CREATE_TABLE);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int saveRegistraionDetails(RegistrationDetails userInfo){
		int result =0;
		PreparedStatement ps = null;
		try{
			getConnection();
			ps = con.prepareStatement(INSERT_REGISTER);
			ps.setString(1,  userInfo.getFullname());
			ps.setString(2, userInfo.getEmail());
			ps.setString(3, userInfo.getUsername());
			ps.setString(4, userInfo.getPassword());
			result =ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public void updateEmail(String email, String loginId){
		PreparedStatement ps =null;
		try{
			getConnection();
			ps =con.prepareStatement(UPDATE_EMAIL);
			ps.setString(1, email);
			ps.setString(2, loginId);
			
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public boolean getUserDetails(String loginId, String password){
		boolean result=false;
		PreparedStatement ps =null;
		try{
			getConnection();
			ps =con.prepareStatement(SELECT_USER_INFO);
			ps.setString(1, loginId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				String e =rs.getString("email");
				String f =rs.getString("fullname");
				System.out.println("Emial:: " + e + " fullName:: " + f);
				result=true;
			}else{
				result=false;
				System.out.println("Your're not in the system, Please register first");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
		
		
	}
	
}
