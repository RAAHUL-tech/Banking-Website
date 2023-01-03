/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bank;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Cryptologin extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cryptologin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cryptologin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname=request.getParameter("uname");
        String pass=request.getParameter("pass");
        String pass2="";
        try
        {
           String passhash=toHexStr(obtainSHA(pass)); 
           try
           {
               Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
                  
                        PreparedStatement stmt=con.prepareStatement("select password from cryptoacc where uname=?");
			stmt.setString(1, uname);
                        ResultSet rs=stmt.executeQuery();
                        while(rs.next())
                        {
                            pass2=rs.getString(1);
                        }
			con.close(); 
                        if(pass2.equals(passhash))
                        {
                          RequestDispatcher rd=request.getRequestDispatcher("cryptocustomer.jsp");
                          rd.forward(request, response);
                        }
                        else
                        {
                            response.setContentType("text/html;charset=UTF-8");
                  PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Secure bank</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Login Failed</h3>");
            out.println("<a href=\"bitcoinlogin.html\">Try Again</a> ");
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

 public static byte[] obtainSHA(String s) throws NoSuchAlgorithmException
    {
        MessageDigest msgDgst=MessageDigest.getInstance("SHA-512");
        return msgDgst.digest(s.getBytes(StandardCharsets.UTF_8));
    }
    public static String toHexStr(byte[] hash)
    {
        BigInteger no=new BigInteger(1,hash);
        StringBuilder sb=new StringBuilder(no.toString(16));
        while(sb.length()<32)
        {
            sb.insert(0, '0');
        }
        return sb.toString();
    }
}
