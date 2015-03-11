<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello from MVC</title>
</head>
<body>
<table border="1">  
   <tr>  
    <td class="heading">User Id</td>  
    <td class="heading">First Name</td>  
    <td class="heading">Last Name</td>  
    <td class="heading">Gender</td>  
    <td class="heading">City</td>  
    <td class="heading">Edit</td>  
    <td class="heading">Delete</td>  
   </tr>  
   <c:forEach var="user" items="${userList}">  
    <tr>  
     <td>${user.id}</td>  
     <td>${user.name}</td>  
    </tr>  
   </c:forEach>  
   <tr><td colspan="7"><a href="register">Add New User</a></td></tr>  
  </table>  
</body>
</html>