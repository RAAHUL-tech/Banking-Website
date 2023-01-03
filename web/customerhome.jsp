

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure Bank</title>
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: sans-serif;
            }
            
            #container1{
                display: flex;
                justify-content: center;
                align-items: center;
                margin-left: auto;
                margin-right: auto;
                margin-top: 10%;
                
                max-width: 600px;
                width: 100%;
                background: #fff;
                padding: 25px 30px;
                border-radius: 5px;
                box-shadow: 10px 10px 8px 10px #888888;
            }
            #contents a{
                padding: 10px;
                padding-top: 10px;
            }
            #contents a button{
                width: 160px;
                height: 38px;
            }
            #contents form{
                padding: 10px;
            }
            #contents form button{
                width: 160px;
                height: 35px;
            }
            #inner{
                display: flex;
                
            }
            #container2{
                
                margin-left: auto;
                margin-right: auto;
                margin-top: 10%;
                
                max-width: 600px;
                width: 100%;
                background: #fff;
                padding: 25px 30px;
                border-radius: 5px;
                box-shadow: 10px 10px 8px 10px #888888;
            }
            #container2 h2{
                font-size: 40px;
                font-weight: 600;
                margin-bottom: 15px;color: hsl(233,26%,24%);
                line-height: 1.1;
            }
            #container2 p{
                color: hsl(233,8%,62%);
                margin-bottom: 15px;
                font-weight: 700;
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
        </style>
    </head>
    <body>
        <header id="header">
    
        <%
            String accno=request.getParameter("accno");
            session.setAttribute("user",accno);
            
            %>
            <h3 id="accno">Account No :<%=accno%></h3>
           <a href="logout.jsp">Log out</a>
        </header>  
        <div id="inner">
           <section id="container2">
               <h2>Important Info</h2>
               <p>All your transactions are stored by secure hashing algorithm.<br /><br/>
               Don't share your password and otp number with others.!!Happy banking..<br />
               </p>
           </section>
           <section id="container1">
               <div id="contents">
                   <form action="netacc" method="post">
                       <button id="debit" type="submit">Pay to another account</button>
                   </form>
                   <form action="balance" method="post">
                       <button id="balance" type="submit">Check bank balance</button>
                   </form>
                   <form action="estatement" method="post">
                       <button id="estatement" type="submit">Get e-statement</button>
                   </form>
                   <form action="complaintredirect" method="post">
                       <button id="complaintredirect" type="submit">File a complaint</button>
                   </form>
                   
                    
                   
               </div>
           </section>
           </div>
            
    </body>
</html>
