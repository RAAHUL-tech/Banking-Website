
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure bank-e statement</title>
        <%@page import="java.sql.ResultSet"%>
        <%@page import="java.sql.Connection"%>
        <%@page import="java.sql.DriverManager"%>
        <%@page import="java.sql.PreparedStatement"%>
        <style>
            #header
            {
                color:#ffffff;
                height: 50px;
                background: rgb(20%,40%,40%);
                display: flex;
                align-items: center;
                justify-content: space-around;
            }
            #accno{
                margin-right: 10px;
                flex: 0.5;
            }
            #header a{
                text-decoration: none;
                color: black;
                padding: 5px;
            }
            #header a:hover{
                color: whitesmoke;
            }
            #header a:active{
                color: red;
            }
            table{
                margin-left: 30px;
            }
            #estatement h3{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <header id="header">
    
        <%
            String accno=(String)session.getAttribute("user");
            
            %>
            <h3 id="accno">Account No :<%=accno%></h3>
           <a href="logout.jsp">Log out</a>
        </header> 
           <div id='estatement'>
               <h3>E-statement</h3>
               <%
                   try{
                   Class.forName("com.mysql.jdbc.Driver");
                   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/securebank","root","root123");
                   PreparedStatement stmt=con.prepareStatement("select * from transactions where accnofrom=?");
                   stmt.setString(1, accno);
                   ResultSet rs=stmt.executeQuery();
                   %>
                   <table border='2'>
                       <tr>
                           <th>Transaction id</th>
                           <th>Accno1</th>
                           <th>Accno2</th>
                           <th>Mode</th>
                           <th>Type</th>
                           <th>Bank</th>
                           <th>Amount</th>
                           <th>Description</th>
                           <th>Date</th>
                           <th>Time</th>
                           <th>Status</th>
                       </tr>
                       <% while(rs.next()){ %>
                       <tr>
                           <td><%=rs.getString(1)%></td>
                           <td><%=rs.getString(2)%></td>
                           <td><%=rs.getString(3)%></td>
                           <td><%=rs.getString(4)%></td>
                           <td><%=rs.getString(5)%></td>
                           <td><%=rs.getString(6)%></td>
                           <td><%=rs.getString(7)%></td>
                           <td><%=rs.getString(8)%></td>
                           <td><%=rs.getString(9)%></td>
                           <td><%=rs.getString(10)%></td>
                           <td><%=rs.getString(11)%></td>
                       </tr>
                       <%} %>
                   </table>
           </div>  
                       <%  }
catch(Exception e)
{
e.printStackTrace();
}
%>
    </body>
</html>
