<%-- 
    Document   : webshop
    Created on : Sep 21, 2017, 11:36:04 PM
    Author     : david
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="shoppingList" method="post">
            <h1>Shoppinglist</h1>
            
            <p><input type="submit" value="Add item"> <input type="text" name="newItem"></p> 
        </form>
        <br>
        
        <c:forEach var="obj" items="${itemEAO.itemList}">    
            <form action="shoppingList" method="post">
		<p><input type="submit" value="Delete"/> ${obj.iname}</p>
                <p><input type="hidden" name="deleteItem" value="${obj.id}" ></p>
            </form>
        </c:forEach>
            
    </body>
</html>
