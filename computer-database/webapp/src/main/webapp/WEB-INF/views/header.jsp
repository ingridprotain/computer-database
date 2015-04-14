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
        	<my:link name="Application - Computer Database" target="/webapp/dashboard" myClass="navbar-brand"/>
        	<div class="pull-right">
	        	<a href="?lang=en"><img alt="uk" src="${pageContext.request.contextPath}/static/img/flag_uk.png" /></a>
	   		<a href="?lang=fr"><img alt="fr" src="${pageContext.request.contextPath}/static/img/flag_fr.png" /></a>
   			</div>
        </div>
    </header>