
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session = "false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head
    >
    <body>
        <p><font color="red"><%= request.getAttribute("errorMessage") %></font></p>
        <form name="formName" action="loggin" method="POST">
            <fieldset>
                <legend>Loggin</legend>
                <p>Password: <input type="text" name="password"></p>
                <p><input type="submit" value="Logg inn"></p>
            </fieldset>
        </form>
    </body>
</html>
