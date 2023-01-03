/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.math.BigInteger;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Newuser extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Newuser</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Newuser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname=request.getParameter("firstname");
        String lastname=request.getParameter("lastname");
        String gender=request.getParameter("gender");
        String dob=request.getParameter("dob");
        String accno=request.getParameter("accno");
        String acctype=request.getParameter("acctype");
        String ifsc=request.getParameter("ifsc");
        String branch=request.getParameter("branch");
        String email=request.getParameter("email");
        String mobile=request.getParameter("mobile");
        String pass=request.getParameter("pass");
        String pass2=request.getParameter("pass2");
        try
        {
            String saltvalue=PassBasedEnc.getSaltvalue(32);
            String hash=PassBasedEnc.generateSecurePassword(pass,saltvalue);
            String hash2=PassBasedEnc.generateSecurePassword(pass2,saltvalue);
            if(hash.equals(hash2))
            {
                try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
				// inserting new user record in the database.
				PreparedStatement stmt1=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt1.setString(1, accno);
				stmt1.setString(2, firstname);
				stmt1.setString(3, lastname);
                                stmt1.setString(4, acctype);
				stmt1.setString(5, ifsc);
				stmt1.setString(6, branch);
                                stmt1.setString(7, email);
                                stmt1.setString(8, dob);
                                stmt1.setString(9, gender);
                                stmt1.setString(10, mobile);
                                stmt1.setString(11, saltvalue);
                                stmt1.setString(12, hash);
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
            out.println("<h3>Account registered succesfully</h3>");
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
            out.println("<a href=\"newcustomer.html\">Try again</a> ");
            out.println("</body>");
            out.println("</html>");
            }
       
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
   /* private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom random=new SecureRandom();
        byte[] salt=new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    public static String getSecurePassword(String pass,byte[] salt)
    {
      String generatedPassword =null;
      try
      {
          MessageDigest md=MessageDigest.getInstance("SHA-512");
          md.update(salt);
          byte[] bytes=md.digest(pass.getBytes(StandardCharsets.UTF_8));
          StringBuilder sb=new StringBuilder();
          for(int i=0;i<bytes.length;i++)
          {
              sb.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
              
          }
          generatedPassword=sb.toString();
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return generatedPassword;
    }
 */

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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
