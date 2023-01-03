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


public class transfercrypto extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet transfercrypto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet transfercrypto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        String uname=(String)session.getAttribute("user");
        String addr1=request.getParameter("addr1");
        String addr2=request.getParameter("addr2");
        String type=request.getParameter("type");
        String amt=request.getParameter("amt");
        String des=request.getParameter("des");
        session.setAttribute("addr1", addr1);
        session.setAttribute("addr2", addr2);
        session.setAttribute("type", type);
        session.setAttribute("amt", amt);
        session.setAttribute("des", des);
 
            RequestDispatcher rd=request.getRequestDispatcher("/cryptowait.jsp");
            rd.forward(request,response);
     
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
