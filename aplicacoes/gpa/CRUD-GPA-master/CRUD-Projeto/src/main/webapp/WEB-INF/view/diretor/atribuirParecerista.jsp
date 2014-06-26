<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

	<head>
		<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"></script>
		<meta charset="utf-8">
		<link href="<c:url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/estilo.css" />" rel="stylesheet"/>
			<title>Atribuir Parecerista</title>
	</head>

	<body>
	
		<form:form id="usuario" method="post" role="form" class="form-horizontal">
			
			<br /> <br />
			<input type="hidden" name="projetoId" value="${projetoId}">
			
			Atribuir Parecerista: <select name="parecerista">
				<c:forEach items="${usuarios}" var="usuario">
					<option value="${usuario.id}">${usuario.nome}</option>
				</c:forEach>
			</select>	
			
			<br /> <br />
			
			Data: <input type="date" min="0" max="5"> 
			
			<br /> <br />
		
			<button type="button" class="btn btn-success">Atribuir Parecerista</button>
		</form:form>
		
	</body>
	
</html>