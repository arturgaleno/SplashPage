<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertTemplate template="../template/template.jsp">

<tiles:putAttribute name="css">
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css-resources/general.css">
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css-resources/splash.css">
</tiles:putAttribute>

<tiles:putAttribute name="content">
	<section class="col-sm-12 col-md-12 col-lg-12">
		<div class="row">
			<article>
				<div id="q1Chart" class="col-sm-6 col-md-6 col-lg-6">
					
				</div>
				<div id="q2Chart" class="col-sm-6 col-md-6 col-lg-6">
					
				</div>
				<div id="q3Chart" class="col-sm-6 col-md-6 col-lg-6">
					
				</div>
			</article>
		</div>
	</section>
</tiles:putAttribute>

<tiles:putAttribute name="script">
	<script type="text/x-template" id="form_template">
		<input name="inputEmail" id="inputEmail" placeholder="Email address" class="input_email" type="email">
		<button id="btnSubmit" type="submit" class="btn btn_submit">Send for free</button>
	</script>
	<script src="${pageContext.request.contextPath}/js-resourses/pagescripts/alertmessage.js"></script>
	<script src="${pageContext.request.contextPath}/js-resourses/highchart/highcharts.js"></script>
	<script src="${pageContext.request.contextPath}/js-resourses/pagescripts/report.js"></script>
</tiles:putAttribute>
	
</tiles:insertTemplate>