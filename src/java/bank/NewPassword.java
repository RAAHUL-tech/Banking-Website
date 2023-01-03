/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;   
import java.sql.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class NewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String newPassword = request.getParameter("password");
		String confPassword = request.getParameter("confPassword");
		RequestDispatcher dispatcher = null;
		try
        {
            String saltvalue=PassBasedEnc.getSaltvalue(32);
            String hash=PassBasedEnc.generateSecurePassword(newPassword,saltvalue);
            String hash2=PassBasedEnc.generateSecurePassword(confPassword,saltvalue);
            if(hash.equals(hash2))
            {
                try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
				// inserting new user record in the database.
				PreparedStatement stmt1=con.prepareStatement("update customer set password=? , salt=? where email=?");
				stmt1.setString(1, hash);
                                stmt1.setString(2, saltvalue);
				stmt1.setString(3, (String) session.getAttribute("email"));
				stmt1.executeUpdate();
                        
			    
			    con.close();
                            response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Secure bank</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Password changed succesfully</h3>");
            out.println("<a href=\"login.html\">Click here</a> ");
            out.println("</body>");
            out.println("</html>");
			   
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
            }
            else
            {
                response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Secure bank-ERROR</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Password Mismatch</h3>");
            out.println("<a href=\"newPassword.jsp\">Try again</a> ");
            out.println("</body>");
            out.println("</html>");
            }
       
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
      
}
class PassBasedEnc
{
    private static final Random random=new SecureRandom();
    private static final String characters="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int iterations=1000;
    private static final int keylength=512;
    
    public static String getSaltvalue(int length)
    {
        StringBuilder finalval=new StringBuilder(length);
        for(int i=0;i<length;i++)
        {
            finalval.append(characters.charAt(random.nextInt(characters.length())));
        }
        return new String(finalval);
    }
    
    public static byte[] hash(char[] password,byte[] salt)
    {
        PBEKeySpec spec=new PBEKeySpec(password,salt,iterations,keylength);
        Arrays.fill(password, Character.MIN_VALUE);
        try
        {
            SecretKeyFactory skf=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return skf.generateSecret(spec).getEncoded();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            spec.clearPassword();
        }
        return null;
    }
    public static String generateSecurePassword(String password,String salt)
    {
        String finalval=null;
        byte[] securePassword=hash(password.toCharArray(),salt.getBytes());
        finalval=Base64.getEncoder().encodeToString(securePassword);
        return finalval;
    }
    
}

