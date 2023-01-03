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
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class mine extends HttpServlet {
    
    private int index;
    private long timestamp;
    private String data;
    private String prevhash;
    private String hash;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet mine</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mine at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String data1=request.getParameter("detail");
        HttpSession session=request.getSession();
        String uname=(String)session.getAttribute("user");                   
        String id="",hash1="";
         this.data=data1;
         this.timestamp=System.currentTimeMillis();
         try
         {
             Class.forName("com.mysql.jdbc.Driver");
	     Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery("SELECT id,hash FROM blocks ORDER BY id DESC LIMIT 1");
             while(rs.next())
                        {
                            id=rs.getString(1);
                            hash1=rs.getString(2);
                        }
             con.close(); 
             this.prevhash=hash1;
             int index1=Integer.parseInt(id);
             this.index=index1+1;
             String h1=calculateHash();
             this.hash=h1;
             String dbindex=Integer.toString(this.index);
             String dbtime=String.valueOf(this.timestamp);
             Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
             PreparedStatement stmt1=con2.prepareStatement("insert into blocks values(?,?,?,?,?)");
	     stmt1.setString(1, dbindex);
             stmt1.setString(2, this.hash);
             stmt1.setString(3, this.data);
             stmt1.setString(4, dbtime);
             stmt1.setString(5, this.prevhash);
             stmt1.executeUpdate();		    
	    con2.close();
            session.setAttribute("index", dbindex);
            session.setAttribute("data", this.data);
            session.setAttribute("hash", this.hash);
            session.setAttribute("prevhash", this.prevhash);
            session.setAttribute("time", dbtime);
            RequestDispatcher rd=request.getRequestDispatcher("/successmine.jsp");
            rd.forward(request,response);
         }
         catch(Exception e)
         {
             e.printStackTrace(); 
         }
   
    }
    
    public String calculateHash()
    {
        String text=String.valueOf(this.index+this.prevhash+String.valueOf(this.timestamp)+String.valueOf(this.data));
        final StringBuilder sb=new StringBuilder();
        try{
           MessageDigest digest=MessageDigest.getInstance("SHA-256");
           final byte bytes[]=digest.digest(text.getBytes());
           for(final byte b:bytes)
           {
               String hex=Integer.toHexString(0xff &b);
               if(hex.length()==1)
               {
                   sb.append('0');
               }
               sb.append(hex);
           }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return sb.toString();
    }


}
