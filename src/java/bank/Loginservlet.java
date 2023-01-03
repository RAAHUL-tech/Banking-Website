/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bank;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;    
import java.util.*;
import java.sql.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class Loginservlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Loginservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Loginservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accno=request.getParameter("accno");
        String pass=request.getParameter("pass");
        String hash="",salt="";
        
            try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
                  
                        PreparedStatement stmt=con.prepareStatement("select salt,password from customer where accno=?");
			stmt.setString(1, accno);
                        ResultSet rs=stmt.executeQuery();
                        while(rs.next())
                        {
                            salt=rs.getString(1);
                            hash=rs.getString(2);
                        }
			con.close();                       
                        String hash2=PassBasedEnc.generateSecurePassword(pass,salt);
                        if(hash.equals(hash2))
                        {
                            HttpSession session=request.getSession();
                            session.setAttribute("accno", accno);
                            RequestDispatcher rd=request.getRequestDispatcher("/customerhome.jsp");
                            rd.forward(request,response);
                        }
                        else
                        {
                              response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Secure bank-ERROR</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Login failed</h3>");
            out.println("<a href=\"login.html\">Try again</a> ");
            out.println("</body>");
            out.println("</html>");
                        }  
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
    }
    
    /*private static byte[] getSalt() throws NoSuchAlgorithmException
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
    }  */
 /*public static byte[] getSHA(String input)
 {
     try
     {
         MessageDigest md=MessageDigest.getInstance("SHA-512");
         return md.digest(input.getBytes(StandardCharsets.UTF_8));
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
     return null;
 }
 public static String toHexString(byte[] hash)
 {
     BigInteger number=new BigInteger(1,hash);
     StringBuilder hexString=new StringBuilder(number.toString(16));
     while(hexString.length()<32)
     {
         hexString.insert(0, '0');
     }
     return hexString.toString();
 } */


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
