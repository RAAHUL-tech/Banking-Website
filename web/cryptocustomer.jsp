
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure bank-Crypto customer</title>
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: sans-serif;
            }
            
            #container1{
                display: flex;
                flex-direction: column;
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
                display: flex;
                flex-direction: column;
                text-decoration: none;
                padding: 5px;
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
            String uname=request.getParameter("uname");
            session.setAttribute("user",uname);
            
            %>
            <h3 id="accno">User :<%=uname%></h3>
           <a href="logout.jsp">Log out</a>
        </header> 
           <div id="inner">
           <section id="container2">
               <h2>Important Info</h2>
               <p>All you crypto transactions are stored by secure hashing algorithm.<br /><br/>
               Once you complete the transaction wait for 24-hours for successfull transaction.<br />
               </p>
           </section>
           <section id="container1">
               <div id="contents">
                   
                   <a href="minedetails.jsp"><button id="mine" type="submit">Mine Bitcoin</button></a>
                  
                   
                   <a href="transferbitcoin.jsp"><button id="transfer" type="submit">Transfer Bitcoin</button></a>
                   
                   
                     <a href="https://www.coindesk.com/price/bitcoin/" target="_blank"><button id="price" type="submit">Get Current Bitcoin price</button></a>
                   
               </div>
           </section>
           </div>
    </body>
</html>
