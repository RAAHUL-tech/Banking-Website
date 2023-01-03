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


public class Netbank extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Netbank</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Netbank at " + request.getContextPath() + "</h1>");
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
        try
        {
            
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
                            session.setAttribute("user",accno);
                            RequestDispatcher rd=request.getRequestDispatcher("netbankdebit.jsp");
                            rd.forward(request,response);
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
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    

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

