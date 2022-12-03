<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Route edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create route</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit route #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/route">
				<input type="hidden" name="id" value="${dto.id}" />
	
		<div class="row">
			<div class="row">
				<div class="input-field col s12">
					<input type="text" name="name" required minlength=2 maxlength=20 value="${dto.name}"> <label for="name">Name</label>
				</div>
			
			<div class="row">
				<div class="input-field col s6">
					<input type="number" name="countStops" required min=1 max=35 value="${dto.countStops}"> <label for="countStops">Count Stops</label>
				</div>
				
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="number" name="duration" required min=1 max=60 value="${dto.duration}"> <label for="duration">duration</label>
				</div>
				
			</div>
		</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/route"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>