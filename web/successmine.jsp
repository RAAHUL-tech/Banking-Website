

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mining successful</title>
    </head>
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
        *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: sans-serif;
            }
            table{
                margin-left: 30px;
                text-align: center;
            }
            
    </style>
    <body>
        <header id="header">
    
        <%
            String uname=(String)session.getAttribute("user");
            String index=(String)session.getAttribute("index");
             String hash=(String)session.getAttribute("hash");
             String data=(String)session.getAttribute("data");
             String prevhash=(String)session.getAttribute("prevhash");
             String time=(String)session.getAttribute("time");
            %>
            <h3 id="accno">User :<%=uname%></h3>
           <a href="logout.jsp">Log out</a>
        </header> 
          <table border='2'>
              <tr>
                           <th>Block index</th>
                           <th>Data</th>
                           <th>Hash</th>
                           <th>Prevhash</th>
                           <th>Timestamp</th>
                       </tr>
                   <tr>
                           <td><%=index%></td>
                           <td><%=data%></td>
                           <td><%=hash%></td>
                           <td><%=prevhash%></td>
                           <td><%=time%></td>
                       </tr>
          </table>
    </body>
</html>
