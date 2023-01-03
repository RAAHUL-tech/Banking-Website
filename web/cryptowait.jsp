

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crypto-Confirm</title>
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
            #class{
                display: flex;
                height: 100vh;
                justify-content: center;
                align-items: center;
            }
            .container{
                max-width: 600px;
                width: 100%;
                background: #fff;
                padding: 25px 30px;
                border-radius: 5px;
                box-shadow: 10px 10px 8px 10px #888888;
            }
            .container .title{
                font-size: 25px;
                font-weight: 500;
            }
            .container .title::before{
                content: '';
                position: absolute;
                height: 3px;
                width: 30px;
               
            }
            .container form .user-details{
                display: flex;
                flex-direction: column;
                flex-wrap: wrap;
                justify-content: space-between;
                align-items: center;
                margin: 20px 0 12px 0;
            }
            form .user-details .input-box{
                width: calc(100% / 2 - 20px);
                margin-bottom: 15px;
            }
            .user-details .input-box .details{
                font-weight: 500;
                margin-bottom: 5px;
                display: block;
            }
            .user-details .input-box input{
                height: 30px;
                width: 100%;
                outline: none;
                border-radius: 5px;
                border: 1px solid #ccc;
                padding-left: 15px;
                font-size: 16px;
                border-bottom-width: 2px;
                transition: all 0.3s ease;
            }
            .user-details .input-box input:focus,
            .user-details .input-box input:valid{
                border-color: #9b59b6;
            }
            .container a{
                text-decoration: none;
                position: relative;
                top: 20px;
            }
            form .button button{
               
                width: 150px;
                height: 35px;
            }  
            .input-box.success input{
                border-color: #09c372;
            }
            .input-box.error input{
                border-color: #ff3860;
            }
            .input-box .error{
                color: #ff3860;
                font-size: 11px;
                height: 13px;
            }
        </style>
    </head>
    <body>
        <header id="header">
    
        <%
            String uname=(String)session.getAttribute("user");
            
            
            %>
            <h3 id="accno">User :<%=uname%></h3>
           <a href="logout.jsp">Log out</a>
        </header> 
           <div id="class">
          <div class="container">
             <div class="title">Crypto Pay-Confirm</div>
             <form id="form" action="cryptoconfirm" method="post" autocomplete="off">
                <div class="user-details">
                   
       <div class="input-box">
        <span class="details">Your Crypto address</span>
        <%  String addr1=(String)session.getAttribute("addr1");  %>
        <input type="text" id="addr1" name="addr1" disabled value=<%=addr1%>>
       <div class="error" id="error1"></div>
       </div>
       <div class="input-box">
        <span class="details">Crypto address of person you want to send</span>
        <%  String addr2=(String)session.getAttribute("addr2");  %>
       <input type="text" id="addr2" name="addr2" disabled value=<%=addr2%>>
       <div class="error" id="error2"></div>
       </div>
                    <div class="input-box">
                        <span class="details">Crypto type</span>
                        <%  String type=(String)session.getAttribute("type");  %>
                        <input type="text" id="type" name="type" disabled value=<%=type%>>
                        <div class="error" id="error3"></div>
                </div>
         <div class="input-box">
        <span class="details">Amount</span>
         <%  String amt=(String)session.getAttribute("amt");  %>
       <input type="number" id="amt" name="amt" disabled value=<%=amt%>>
       <div class="error" id="error4"></div>
       </div>
        <div class="input-box">
        <span class="details">Description</span>
        <%  String des=(String)session.getAttribute("des");  %>
       <input type="text" id="des" name="des" disabled value=<%=des%>>
       <div class="error" id="error5"></div>
       </div>
       <div class="button">
        <button type="submit" id="btn">Confirm Pay</button>
       </div>
        </div>
       </form>
            
        </div>
           </div>
    </body>
</html>
