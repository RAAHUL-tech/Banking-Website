

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure bank</title>
    </head>
    <body>
        <%
            session.invalidate();
            %>
        <h3>Session closed</h3>
        <a href="index.html">Click here</a>to redirect to home page
    </body>
</html>
