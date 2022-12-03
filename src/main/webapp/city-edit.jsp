<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="City edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create city</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit city #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/city">
			<input type="hidden" name="id" value="${dto.id}" />
			
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="name" required minlength=2 maxlength=20 value="${dto.name}"> <label for="name">name</label>
				</div>
				<div class="col s6">
					<label for="countryId">Country ID</label> 
					<select name="countryId" class="browser-default" required>
						<option value="">--select country id--</option>
						<c:forEach items="${allCountry}" var="country">
							<option value="${country.id}" <c:if test="${country.id eq dto.countryId}">selected="selected"</c:if>>${country.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="row">
				<div class="col s6">
					<input type="number" name="countStreets" required min=1 max=100 value="${dto.countStreets}"> <label for="ownerId">countStreets</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/city"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>