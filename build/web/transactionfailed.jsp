

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure bank-Transaction failed!!</title>
        <style>
            h1{
                color: red;
                font-weight: 800;
            }
        </style>
    </head>
    <body>
        <%
            session.invalidate();
            %>
        <h1>Transaction Failed!!</h1>
        <h5>Possible solutions</h5>
        <p>
        <ul>
            <li>Check your bank balance.The amount you entered might exceed the actual amount in your account.</li>
            <li>Make sure you have a reliable net-connection!!</li>
            <li>Our servers may run slow.Try again after some time!!</li>
        </ul>
        </p>
        <a href="index.html">Click here</a>to redirect to home page
    </body>
</html>
