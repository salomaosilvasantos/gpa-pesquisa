<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Informações do Participante</title>
<link
	href="<c:url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/estilo.css" />"
	rel="stylesheet" />
</head>

<body onload="verificarSeExisteUlNaPagina()">
	<jsp:include page="../modulos/header.jsp" />

	<div class="container" style="margin-bottom: 70px;">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Detalhes do Participante</h2>

				<table id="details">
					<tr>
						<td class="head">Nome:</td>
						<td class="content">${usuario.nome}</td>
					</tr>

					<tr>
						<td class="head">CPF:</td>
						<td class="content">${usuario.cpf}</td>
					</tr>

					<tr>
						<td class="head">Email:</td>
						<td class="content">${usuario.email}</td>
					</tr>

					<tr>
						<td class="head" valign="top">Projetos Envolvidos:</td> 
				        
						<c:forEach items="${usuario.projetos}" var="projeto">
							<td class="content"><a
								href="<c:url value="/projeto/${projeto.id}/detalhes" ></c:url>">${projeto.nome}</a></td>
						</c:forEach>
						

						<c:forEach items="${usuario.projetosEnvolvidos}" var="projeto">
							<td class="content"><a
								href="<c:url value="/projeto/${projeto.id}/detalhes" ></c:url>">${projeto.nome}</a></td>
						</c:forEach>
						
					</tr>
					

				</table>


			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp" />


	
</body>
</html>
