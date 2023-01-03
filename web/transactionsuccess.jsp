

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure-bank-Success</title>
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: sans-serif;
            }
            body{
                display: flex;
                height: 100vh;
                justify-content: center;
                align-items: center;
                
            }
            .container{
                max-width: 1000px;
                width: 100%;
                background: #fff;
                padding: 25px 30px;
                border-radius: 5px;
                box-shadow: 10px 10px 8px 10px #888888;
            }
            .container .title{
                font-size: 25px;
                font-weight: 500;
                color: green;
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
            .user-details .input-box label{
                height: 45px;
                width: 100%;
                outline: none;
                border-radius: 5px;
                border: 1px solid #ccc;
                padding-left: 15px;
                font-size: 16px;
                border-bottom-width: 2px;
                transition: all 0.3s ease;
            }
            .user-details .input-box label:focus,
            .user-details .input-box label:valid{
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
            header{
                position: relative;
                bottom: 45%;
                left: 55%;
            }
            header a{
                text-decoration: none;
            }
            header a:hover{
                color: red;
            }
        </style>
    </head>
    <body>
        <header>
             <a href="logout.jsp">Log out</a>
        </header>
        <div  class="container">
       <div class="title">Status : Success!!</div>
       <form action="sendemailpay" method="post" autocomplete="off">
          <div class="user-details">
           <div class="input-box">
        <span class="details">Transaction id</span>
       <%
          HttpSession mySession = request.getSession();
                    String id=(String)mySession.getAttribute("id");
                    
                    
                    %>
                    <label id="accno" name="accno2"><%=id%></label>
               
       </div>
          <div class="input-box">
        <span class="details">Account No</span>
       <%
           
                    String accno=(String)mySession.getAttribute("accno");
                    
                    %>
                    <label id="accno" name="accno"><%=accno%></label>
               
       </div>
        <div class="input-box">
        <span class="details">Account No of Payee</span>
       <%
          
                    String accno2=(String)mySession.getAttribute("accno2");
                    
                    
                    %>
                    <label id="accno" name="accno2"><%=accno2%></label>
               
       </div>
        <div class="input-box">
        <span class="details">Mode of Payment</span>
        <%
            
                    String mode=(String)mySession.getAttribute("acctype");
                    
                    %>
                    <label id="acctype1" name="acctype"><%=mode%></label>
       </div>
         <div class="input-box">
        <span class="details">Bank-to-Bank</span>
        <%
            
                    String bank=(String)mySession.getAttribute("bank");
                    
                    %>
                    <label id="bank1" name="bank"><%=bank%></label>
       </div>
          <div class="input-box">
        <span class="details">Bank Name</span>
        <%
           
                    String bank2=(String)mySession.getAttribute("bank2");
                    
                    %>
                    <label id="bank2" name="bank1"><%=bank2%></label>
       </div>
       <div class="input-box">
        <span class="details">Amount</span>
        <%
            
                    String amt=(String)mySession.getAttribute("amt");
                
                    %>
                    <label id="amt" name="amt"><%=amt%></label>
       </div>
       <div class="input-box">
        <span class="details">Description</span>
         <%
             
                    String des=(String)mySession.getAttribute("des");
                    
                    %>
                    <label id="des" name="des"><%=des%></label>
       </div>
       <div class="button">
        <button type="submit">Click here</button>
       </div> 
          </div>
       </form>
        </div>
    </body>
</html>
