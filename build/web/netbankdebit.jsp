

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure bank-Customer Banking</title>
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
        
        <div class="title">Net Banking Portal</div>
        <form action="netbankdebit" method="post" autocomplete="off">
            <div class="user-details">
                <div class="input-box">
        <span class="details">Account No</span>
       <%
                    String accno=(String)session.getAttribute("user");
                    
                    %>
                    <input type="number" id="accno" name="accno" value=<%=accno%> disabled>
               
       </div>
         <div class="input-box">
        <span class="details">Account No of Payee</span>
       <input type="number" id="accno2" name="accno2">
       </div> 
      <div class="input-box">
        <span class="details">Mode of Payment</span>
      <select id="acctype1" name="acctype">
                             <option value="null" selected>Not Selected</option>
                            <option value="nfet">NFET</option>
                            <option value="csc">CSC</option>
                            <option value="ib">Internet Banking</option>
                            <option value="debit">Debit</option>
                            <option value="branch">Branch Payment</option>
                       </select>
       </div> 
       <div class="input-box">
        <span class="details">Bank-to-Bank</span>
      <select id="bank1" name="bank">
                             <option value="null" selected>Not Selected</option>
                            <option value="inside">Within Secure bank</option>
                            <option value="outside">Outside Secure bank</option>
                       </select>
       </div> 
        <div class="input-box">
        <span class="details">Bank Name</span>
        <input type="text" id="bank2" name="bank1" placeholder="Required for outside transaction">
       </div>   
          <div class="input-box">
        <span class="details">Amount</span>
        <input type="number" id="amt" name="amt" placeholder="Max. 10Lakhs only/-">
       </div>  
        <div class="input-box">
        <span class="details">Description</span>
       <input type="text" id="des" name="des">
       </div>  
         <div class="button">
        <button onclick="validate()" type="submit">PAY</button>
       </div>       
      
            </div>
        </form>
        </div>
            <script type="text/javascript">
             function validate()
             {
                 var amt=document.getElementById("amt").value;
                 let amount=parseInt(amt);
                 if(amount>1000000)
                 {
                     alert(amt + "should be less than 10 lakhs"); 
                 }
                
             }
            </script>
    </body>
</html>
