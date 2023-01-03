/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bank;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Netdebittransfer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Netdebittransfer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Netdebittransfer at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try
       {
       HttpSession mySession = request.getSession();
       String accno=(String)mySession.getAttribute("accno");
       String accno2=(String)mySession.getAttribute("accno2");
       String mode=(String)mySession.getAttribute("acctype");
       String bank=(String)mySession.getAttribute("bank");
       String bank1=(String)mySession.getAttribute("bank2");
       String amt=(String)mySession.getAttribute("amt");
       String des=(String)mySession.getAttribute("des");
       String date=java.time.LocalDate.now().toString();
       String time=java.time.LocalTime.now().toString();
       int amt1=0;
            amt1=Integer.parseInt(amt);
            int amount=0,amount2=0;
       int useramt=0,amt3=0;
       try{
           Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
			PreparedStatement stmt=con.prepareStatement("select amt from amount where accno=?");
			stmt.setString(1, accno);
                        ResultSet rs=stmt.executeQuery();
                        while(rs.next())
                        {
                            amount=rs.getInt(1);
                        }
			con.close();
                       
                         useramt=amount;              
                        if(amt1<useramt)
                        {
                           String s="ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"abcdefghijklmnopqrstuvwxys"+"1234567890";
                           StringBuilder sb=new StringBuilder(64);
                           for(int i=0;i<64;i++)
                           {
                               int index=(int)(s.length()*Math.random());
                               sb.append(s.charAt(index));
                           }
                           
                           String id=sb.toString(); 
                           mySession.setAttribute("id", id);
                           if(bank.equalsIgnoreCase("outside"))    //outside secure bank
                           {
                               try{
                                  int ufamt=useramt-amt1;
                                  String ufamt2=Integer.toString(ufamt);
                                  mySession.setAttribute("ufamt", ufamt2);
                                  Class.forName("com.mysql.jdbc.Driver");
		            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
				// inserting new user record in the database.
				PreparedStatement stmt1=con1.prepareStatement("update amount set amt=? where accno=?");
                                String ufamt1=Integer.toString(ufamt);
				stmt1.setString(1,ufamt1);
                                stmt1.setString(2, accno);
				stmt1.executeUpdate();
                                //con4.commit();
                                con1.close();
                               }
                               catch(Exception e)
                               {
                                   e.printStackTrace();
                               }
                              Class.forName("com.mysql.jdbc.Driver");
			      Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
				// inserting new user record in the database.
				PreparedStatement stmt2=con2.prepareStatement("insert into transactions values(?,?,?,?,?,?,?,?,?,?,?)");
				stmt2.setString(1, id);
				stmt2.setString(2, accno);
				stmt2.setString(3, accno2);
                                stmt2.setString(4, mode);
				stmt2.setString(5, bank);
				stmt2.setString(6, bank1);
                                stmt2.setString(7, "-"+amt);
                                stmt2.setString(8, des);
                                stmt2.setString(9, date);
                                stmt2.setString(10, time);
                                stmt2.setString(11, "Success");
				stmt2.executeUpdate(); 
                                PreparedStatement stmt3=con2.prepareStatement("insert into transactions values(?,?,?,?,?,?,?,?,?,?,?)");
				stmt3.setString(1, id);
				stmt3.setString(2, accno2);
				stmt3.setString(3, accno);
                                stmt3.setString(4, mode);
				stmt3.setString(5, bank);
				stmt3.setString(6, bank1);
                                stmt3.setString(7, "+"+amt);
                                stmt3.setString(8, des);
                                stmt3.setString(9, date);
                                stmt3.setString(10, time);
                                stmt3.setString(11, "Success");
				stmt3.executeUpdate();
                                //con1.commit();
                                con2.close();
                           }
                           else if(bank.equalsIgnoreCase("inside")){      //inside secure bank
                               try{
                                  int ufamt=useramt-amt1;
                                  String ufamt2=Integer.toString(ufamt);
                                  mySession.setAttribute("ufamt", ufamt2);
                                  Connection con4=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
			         PreparedStatement stmt4=con4.prepareStatement("select amt from amount where accno=?");
			         stmt4.setString(1, accno2);
                               ResultSet rs2=stmt4.executeQuery();
                        while(rs2.next())
                        {
                            amount2=rs2.getInt(1);
                        }
			con4.close();
                        
                         
                          amt3=amount2;
                        
                        int efamt=amt3+amt1;
                        try
                        {
                            Class.forName("com.mysql.jdbc.Driver");
		            Connection con5=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
				// inserting new user record in the database.
				PreparedStatement stmt6=con5.prepareStatement("update amount set amt=? where accno=?");
                                String ufamt1=Integer.toString(ufamt);
				stmt6.setString(1,ufamt1);
                                stmt6.setString(2, accno);
				stmt6.executeUpdate();
                                PreparedStatement stmt5=con5.prepareStatement("update amount set amt=? where accno=?");
                                String efamt1=Integer.toString(efamt);
				stmt5.setString(1,efamt1);
                                stmt5.setString(2, accno2);
				stmt5.executeUpdate();
                               // con4.commit();
                                con5.close();
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
                               Class.forName("com.mysql.jdbc.Driver");
			       Connection con7=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
				// inserting new user record in the database.
				PreparedStatement stmt7=con7.prepareStatement("insert into transactions values(?,?,?,?,?,?,?,?,?,?,?)");
				stmt7.setString(1, id);
				stmt7.setString(2, accno);
				stmt7.setString(3, accno2);
                                stmt7.setString(4, mode);
				stmt7.setString(5, bank);
				stmt7.setString(6, "NULL");
                                stmt7.setString(7, "-"+amt);
                                stmt7.setString(8, des);
                                stmt7.setString(9, date);
                                stmt7.setString(10, time);
                                stmt7.setString(11, "Success");
				stmt7.executeUpdate(); 
                                PreparedStatement stmt8=con7.prepareStatement("insert into transactions values(?,?,?,?,?,?,?,?,?,?,?)");
				stmt8.setString(1, id);
				stmt8.setString(2, accno);
				stmt8.setString(3, accno2);
                                stmt8.setString(4, mode);
				stmt8.setString(5, bank);
				stmt8.setString(6, "NULL");
                                stmt8.setString(7, "+"+amt);
                                stmt8.setString(8, des);
                                stmt8.setString(9, date);
                                stmt8.setString(10, time);
                                stmt8.setString(11, "Success");
				stmt8.executeUpdate(); 
                               // con2.commit();
                                con7.close();
                           }
                           else   //not choosen null values
                           {
                            RequestDispatcher rd=request.getRequestDispatcher("transactionfailed.jsp");
                            rd.forward(request,response);
                           }
                           RequestDispatcher rd=request.getRequestDispatcher("transactionsuccess.jsp");
                           rd.forward(request,response);
                        }
                        else
                        {
                            RequestDispatcher rd=request.getRequestDispatcher("transactionfailed.jsp");
                            rd.forward(request,response);
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

  
}
