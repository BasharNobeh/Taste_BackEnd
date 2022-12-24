

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
 * Servlet implementation class SignupChecker
 */
@WebServlet("/SignupChecker")
public class SignupChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userEmail = request.getParameter("Email");
		String userPassword = request.getParameter("Password");
					String dbDriver = "com.mysql.cj.jdbc.Driver";
				
			        String dbURL = "jdbc:mysql://localhost:3306/tastedb";
			     
			       try {
			    	   Class.forName(dbDriver);
				        Connection con = DriverManager.getConnection(dbURL,"root","Therexkiller1" );
				        
				        String checkerQuery = "SELECT Email,Password FROM tastedb.users Where Email=?;";
				        String addQuery = "INSERT INTO tastedb.users (Email,Password) Values (?,?)";
				        
				        PreparedStatement prepareSQL = con
			                    .prepareStatement(checkerQuery);
				      
			                        prepareSQL.setString(1, userEmail);   
			                      
			            ResultSet resultWithAccounts = prepareSQL.executeQuery();
			            
			            PrintWriter pwriter=response.getWriter();
			            if(resultWithAccounts.next() == true) {
			            	pwriter.println("False");
			            	
			            	
			            
			            }else {
			            	PreparedStatement prepareSQL2 = con
				                    .prepareStatement(addQuery);
			            	prepareSQL2.setString(1, userEmail);
			            	prepareSQL2.setString(2, userPassword);
			            	int row = prepareSQL2.executeUpdate();
			            	pwriter.println("True");
			            }
			    	   
			    	   
				        
				        
			       }catch(Exception error){
			    	   
			       }
	}

}
