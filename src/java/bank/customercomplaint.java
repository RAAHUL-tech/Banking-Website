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
public class customercomplaint extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet customercomplaint</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet customercomplaint at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        String uname=(String)session.getAttribute("user");
        String type=request.getParameter("complainttype");
        String des=request.getParameter("des");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");				
            PreparedStatement stmt1=con.prepareStatement("insert into complaints(accno,type,description) values(?,?,?)");
	    stmt1.setString(1, uname);
	    stmt1.setString(2, type);
	    stmt1.setString(3, des);
	    stmt1.executeUpdate();		    
            con.close();
            RequestDispatcher rd=request.getRequestDispatcher("/complaintreceived.jsp");
            rd.forward(request,response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
