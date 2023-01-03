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
import java.sql.ResultSet;


public class balance extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet balance</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet balance at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        String accno=(String)session.getAttribute("user");
        String balance="";
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
                  
                        PreparedStatement stmt=con.prepareStatement("select amt from amount where accno=?");
			stmt.setString(1, accno);
                        ResultSet rs=stmt.executeQuery();
                        while(rs.next())
                        {
                            balance=rs.getString(1);
                        }
			con.close();  
                       session.setAttribute("balance", balance);
                       RequestDispatcher rd=request.getRequestDispatcher("checkbalance.jsp");
                       rd.forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
