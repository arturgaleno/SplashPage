<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertTemplate template="../template/template.jsp">

<tiles:putAttribute name="css">
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css-resources/general.css">
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css-resources/survey.css">
</tiles:putAttribute>

<tiles:putAttribute name="content">
	<section class="col-sm-12 col-md-12 col-lg-12">
		<div class="row">
			<article>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div class="form_container centered">
						
					</div>
				</div>
			</article>
		</div>
	</section>
</tiles:putAttribute>

<tiles:putAttribute name="script">
	<script type="text/x-template" id="form_template">

			<h1 class="form_header"><strong>Help us</strong> to be better.</h1>
			
			<div class="question">
				<label class="question-label">What do you expected from our company:</label>
				
				<div class="answer">
					<input type="radio" name="q1" id="rbQ1A3" value="3"/><label> Awesome things</label>
				</div>
				
				<div class="answer">
					<input type="radio" name="q1" id="rbQ2A2" value="2"/><label> Not much</label>
				</div>
				
				<div class="answer">
					<input type="radio" name="q1" id="rbQ1A1" value="1"/><label> Nothing</label>
				</div>
			</div>
			<div class="question">
				<label class="question-label">How much you want to pay?</label>
				
				<div class="answer">
					<input type="radio" name="q2" id="rbQ2A3" value="3"/><label> I would like pay my whole salary</label>
				</div>
				
				<div class="answer">
					<input type="radio" name="q2" id="rbQ2A2" value="2"/><label> I would like pay not so much</label>
				</div>
				
				<div class="answer">
					<input type="radio" name="q2" id="rbQ2A1" value="1"/><label> I don't want give my money for you</label>
				</div>
			</div>
			<div class="question">
				<label class="question-label">What you REALLY needs?</label>
				
				<div class="answer">
					<input type="radio" name="q3" id="rbQ3A3" value="3"/><label> I need something that I want</label>
				</div>
				<div class="answer">
					<input type="radio" name="q3" id="rbQ3A2" value="2"/><label> I need food</label>
				</div>
				<div class="answer">
					<input type="radio" name="q3" id="rbQ3A1" value="1"/><label> Nothing, I already have everything</label>
				</div>
			</div>
			<div>
				<button id="btnSend" type="button" class="btn btn_submit">Send</button>
			</div>

	</script>
	<script  type="text/x-template" id="email_notfound_template">
		<h1 class="form_header"><strong>Are you sure? We cannot found your email :(</strong></h1>
	</script>
	<script src="${pageContext.request.contextPath}/js-resourses/pagescripts/alertmessage.js"></script>
	<script src="${pageContext.request.contextPath}/js-resourses/pagescripts/survey.js"></script>
</tiles:putAttribute>
	
</tiles:insertTemplate>