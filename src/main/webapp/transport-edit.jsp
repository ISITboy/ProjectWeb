<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Transport edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create transport</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit transport #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/transport">
			<input type="hidden" name="id" value="${dto.id}" />
			
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="name" required minlength=2 maxlength=20 value="${dto.name}"> <label for="name">name</label>
				</div>
				<div class="col s6">
					<label for="cityId">City ID</label> 
					<select name="cityId" class="browser-default" required>
						<option value="">--select city id--</option>
						<c:forEach items="${allCity}" var="city">
							<option value="${city.id}" <c:if test="${city.id eq dto.cityId}">selected="selected"</c:if>>${city.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col s6">
					<label for="routeId">Route ID</label> 
					<select name="routeId" class="browser-default" required>
						<option value="">--select route id--</option>
						<c:forEach items="${allRoute}" var="route">
							<option value="${route.id}" <c:if test="${route.id eq dto.routId}">selected="selected"</c:if>>${route.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="row">
				<div class="col s6">
					<input type="number" name="inspection" required min=0 max=1 value="${dto.inspection}"> <label for="inspection">inspection</label>
				</div>
				<div class="col s6">
					<input type="number" name="number" required min=1 max=80 value="${dto.number}"> <label for="number">number</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/transport"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>