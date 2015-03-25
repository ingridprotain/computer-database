<!DOCTYPE html>
<%@taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
        	<my:link name="Application - Computer Database" target="dashboard" myClass="navbar-brand"/>
        </div>
    </header>
   	<div style="text-align : right;"><a href="?lang=en">EN</a>/<a href="?lang=fr">FR</a></div>