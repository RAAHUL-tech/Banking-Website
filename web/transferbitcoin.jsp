

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure bank-Transfer</title>
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
        <script>
            function validate()
            {
              var addr1=document.getElementById("addr1");
              var addr2=document.getElementById("addr2");
              var type=document.getElementById("type");
              var amt=document.getElementById("amt");
              var des=document.getElementById("des");
              var err1=document.getElementById("error1");
              var err2=document.getElementById("error2");
              var err3=document.getElementById("error3");
              var err4=document.getElementById("error4");
              var err5=document.getElementById("error5");
              var addr1val=addr1.value.trim();
              var addr2val=addr2.value.trim();
              var typeval=type.value.trim();
              var amtval=amt.value.trim();
              var desval=des.value.trim();
               err1.innerHTML="";
               err2.innerHTML="";
               err3.innerHTML="";
               err4.innerHTML="";
               err5.innerHTML="";
               if(addr1val === '' || addr1val === null || addr1val.length<64){
                   err1.innerHTML="Address is invalid!";
                   return false;
               }
               else if(addr2val === '' || addr2val === null || addr2val.length<64){
                   
                       err2.innerHTML="Address is invalid";
                       return false;
                   }
               else if(typeval === '' || typeval === null || typeval==="null"){
                   
                       err3.innerHTML="Please select type here!";
                       return false;
                   }
                else if(amtval === '' || amtval === null){
                   
                       err4.innerHTML="Please enter amount!";
                       return false;
                   }
                   else if(desval === '' || desval === null){
                   
                       err4.innerHTML="Please enter some description!";
                       return false;
                   }
                   else{
                       return true;
                   }
            }
        </script>
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
             <div class="title">Crypto Pay</div>
             <form id="form" action="transfercrypto" method="post" autocomplete="off" onsubmit='return validate()'>
                <div class="user-details">
                   
       <div class="input-box">
        <span class="details">Your Crypto address</span>
       <input type="text" id="addr1" name="addr1">
       <div class="error" id="error1"></div>
       </div>
       <div class="input-box">
        <span class="details">Crypto address of person you want to send</span>
       <input type="text" id="addr2" name="addr2">
       <div class="error" id="error2"></div>
       </div>
                    <div class="input-box">
                        <span class="details">Crypto type</span>
                        <select id="type" name="type">
                            <option value="null" selected>Not Selected</option>
                            <option value="bitcoin">Bitcoin</option>
                            <option value="etherium">Etherium</option>
                        </select>
                        <div class="error" id="error3"></div>
                </div>
         <div class="input-box">
        <span class="details">Amount</span>
       <input type="number" id="amt" name="amt">
       <div class="error" id="error4"></div>
       </div>
        <div class="input-box">
        <span class="details">Description</span>
       <input type="text" id="des" name="des">
       <div class="error" id="error5"></div>
       </div>
       <div class="button">
        <button type="submit" id="btn">Pay</button>
       </div>
        </div>
       </form>
            
        </div>
           </div>
    </body>
</html>
