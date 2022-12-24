

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginChecker
 */
@WebServlet("/LoginChecker")
public class LoginChecker extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String userEmail = request.getParameter("Email");
String userPassword = request.getParameter("Password");
			String dbDriver = "com.mysql.cj.jdbc.Driver";
		
	        String dbURL = "jdbc:mysql://localhost:3306/tastedb";
	     
	       try {
	    	   Class.forName(dbDriver);
		        Connection con = DriverManager.getConnection(dbURL,"root","Therexkiller1" );
		        
		        String query = "SELECT Email,Password FROM tastedb.users Where Email=?;";
		        PreparedStatement prepareSQL = con
	                    .prepareStatement(query);
		      
	                        prepareSQL.setString(1, userEmail);   
	                      
	            ResultSet resultWithAccounts = prepareSQL.executeQuery();
	            
	            PrintWriter pwriter=response.getWriter();
	            if(resultWithAccounts.next() == true) {
	            	
	            	if(userPassword.equals(resultWithAccounts.getString(2)) ) {
	            		pwriter.println("True");
	            		
	            	}else {
	            		pwriter.println("False");
	            	}
	            	
	            
	            }else {
	            	pwriter.println("False");
	            }
	    	   
	    	   
		        
		        
	       }catch(Exception error){
	    	   
	       }
	}

}
