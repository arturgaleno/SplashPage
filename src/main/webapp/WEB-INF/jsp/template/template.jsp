<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="utf-8">
		<title>Splash Screen</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css-resources/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css-resources/header.css">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<tiles:insertAttribute name="css" />
	</head>
	<body>
	
		<header class="pageHeader">
	      <h1 class="float-l">
	        <a href="#" title="Splash Screen">Splash Screen</a>
	      </h1>
	    </header>
	
		<tiles:insertAttribute name="content" />
		
		<script src="${pageContext.request.contextPath}/js-resourses/jquery/jquery-2.0.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js-resourses/bootstrap/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js-resourses/underscore/underscore-min.js"></script>
		<script src="${pageContext.request.contextPath}/js-resourses/backbone/backbone-min.js"></script>
		<script type="text/x-template" id="errorMsg_template">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only">Error:</span> <\%= message %>
		</script>
		<tiles:insertAttribute name="script" />
	</body>
</html>