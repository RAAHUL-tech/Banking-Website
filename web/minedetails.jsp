

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure bank-mine details</title>
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
            
            .container{
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
                display: inline-block;
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
                margin-top: 10px;
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
    <body>
        <header id="header">
    
        <%
            String uname=(String)session.getAttribute("user");
            
            
            %>
            <h3 id="accno">User :<%=uname%></h3>
           <a href="logout.jsp">Log out</a>
        </header> 
       <div class="container">
             
             <form id="form" action="mine" method="post" autocomplete="off">
                <div class="user-details">
                   
       <div class="input-box">
        <span class="details">Transaction Details</span>
       <input type="text" id="detail" name="detail">
       <div class="error" id="error1"></div>
       </div>
       <div class="button">
        <button type="submit" id="btn">Mine</button>
       </div>
                </div>
       </form>
            
        </div>
    </body>
</html>
