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
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class netbankdebit extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet netbankdebit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet netbankdebit at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        String accno=(String)session.getAttribute("user");
        String accno2=request.getParameter("accno2");
        String mode=request.getParameter("acctype");
        String bank=request.getParameter("bank");
        String bank2=request.getParameter("bank1");
        String amt=request.getParameter("amt");
        String des=request.getParameter("des");
        String email="";
        int amount=Integer.parseInt(amt);
        if(amount>1000000)
        {
            RequestDispatcher rd=request.getRequestDispatcher("logout.jsp");
            rd.forward(request,response);
        }
        else{
            try{
                Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
			 PreparedStatement stmt=con.prepareStatement("select email from customer where accno=?");
			stmt.setString(1, accno);
                        ResultSet rs=stmt.executeQuery();
                        while(rs.next())
                        {
                            email=rs.getString(1);
                        }
			con.close();
                        RequestDispatcher dispatcher = null;
		int otpvalue = 0;
		HttpSession mySession = request.getSession();
		
		if(email!=null || !email.equals("")) {
			// sending otp
			Random rand = new Random();
			otpvalue = rand.nextInt(1255650);

			String to = email;// change accordingly
			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session1 = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("securebank85@gmail.com", "dgxyppzjhfbginaw");
																									// id and
																									// password here
				}
			});
			// compose message
			try {
				MimeMessage message = new MimeMessage(session1);
				message.setFrom(new InternetAddress(email));// change accordingly
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Secure Bank");
				message.setText("Your OTP for Net-Banking is: " + otpvalue +"\n\t"+"Thank you for choosing secure bank!! ");
				Transport.send(message);
				
			}

			catch (Exception e) {
				throw new RuntimeException(e);
			}
			dispatcher = request.getRequestDispatcher("Netbankotp.jsp");
			request.setAttribute("message","OTP is sent to your email id");
			//request.setAttribute("connection", con);
			mySession.setAttribute("otp",otpvalue); 
			mySession.setAttribute("email",email); 
                        mySession.setAttribute("accno",accno); 
                        mySession.setAttribute("accno2",accno2); 
                        mySession.setAttribute("acctype",mode); 
                        mySession.setAttribute("bank",bank); 
                        mySession.setAttribute("bank2",bank2); 
                        mySession.setAttribute("amt",amt); 
                        mySession.setAttribute("des",des); 
			dispatcher.forward(request, response);
			//request.setAttribute("status", "success");
		}
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        
    }
    

    

}
