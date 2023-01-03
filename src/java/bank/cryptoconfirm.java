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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class cryptoconfirm extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cryptoconfirm</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cryptoconfirm at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        String addr1=(String)session.getAttribute("addr1");
        String addr2=(String)session.getAttribute("addr2");
        String type=(String)session.getAttribute("type");
        String amt=(String)session.getAttribute("amt");
        String des=(String)session.getAttribute("des");
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
             PreparedStatement stmt1=con2.prepareStatement("insert into cryptopay values(?,?,?,?,?)");
	     stmt1.setString(1, addr1);
             stmt1.setString(2, addr2);
             stmt1.setString(3, type);
             stmt1.setString(4, amt);
             stmt1.setString(5, des);
             stmt1.executeUpdate();		    
	    con2.close();
            RequestDispatcher rd=request.getRequestDispatcher("/crytologout.jsp");
            rd.forward(request,response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
