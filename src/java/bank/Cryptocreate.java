/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bank;

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


public class Cryptocreate extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cryptocreate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cryptocreate at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String uname=request.getParameter("uname");
       String email=request.getParameter("email");
       String pass=request.getParameter("pass");
       String cpass=request.getParameter("cpass");
       try{
           String emailhash=toHexStr(obtainSHA(email));
           String passhash=toHexStr(obtainSHA(pass));
           String cpasshash=toHexStr(obtainSHA(cpass));
           if(passhash.equals(cpasshash))
           {
               try
               {
                   Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
				// inserting new user record in the database.
				PreparedStatement stmt1=con.prepareStatement("insert into cryptoacc values(?,?,?)");
				stmt1.setString(1, uname);
				stmt1.setString(2, emailhash);
				stmt1.setString(3, passhash);
				stmt1.executeUpdate();		    
			    con.close();
                            
                      response.setContentType("text/html;charset=UTF-8");
                  PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Secure bank</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Account registered succesfully</h3>");
            out.println("<a href=\"bitcoinlogin.html\">Click here</a> ");
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Secure bank</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Password Dosen't Match!!</h3>");
            out.println("<a href=\"cryptocreate.html\">Try Again</a> ");
            out.println("</body>");
            out.println("</html>"); 
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
