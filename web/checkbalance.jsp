

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure bank-Balance</title>
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: sans-serif;
            }
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
            
            #balance{
                font-size: 30px;
                text-align: center;
                margin-top: 20%;
            }
        </style>
    </head>
    <body>    
        <header id="header">
    
        <%
            HttpSession session1=request.getSession();
            String accno=(String)session1.getAttribute("accno");
            
            %>
            <h3 id="accno">Account No :<%=accno%></h3>
           <a href="logout.jsp">Log out</a>
        </header>  
            <%
                String balance=(String)session1.getAttribute("balance");;
                
                %>
                <div id='balance'>Balance :&#8360;"<%=balance%>"</div>
    </body>
</html>
