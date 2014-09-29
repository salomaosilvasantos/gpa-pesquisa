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
<title>Parecer do Projeto</title>
<link
	href="<c:url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/estilo.css" />"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="../modulos/header.jsp" />
	<div class="container" style="margin-bottom: 70px;">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Parecer do Projeto</h2>
				<table id="details">
					<tr>
						<td class="head">Parecer:</td>
						<td class="content">${projeto.pareceres[0].comentario}</td>
					</tr>
					<tr>
						<td class="head">Reponsavel:</td>
						<td class="content">${projeto.pareceres[0].usuario.nome }</td>
					</tr>
					<tr>
						<td class="head">Prazo:</td>
						<td class="content"><fmt:formatDate pattern="dd-MM-yyyy"
								value="${projeto.pareceres[0].prazo}" /></td>
					</tr>

					<tr>
						<td class="head" valign="top">Arquivos:</td>
						<c:forEach var="documento" items="${projeto.documentos}">
							<td class="content"><a
								href="<c:url value="/documento/${documento.id}"></c:url>">${documento.nomeOriginal}</a></td>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../modulos/footer.jsp" />
	<script src="<c:url value="/webjars/jquery/2.1.0/jquery.min.js" />"></script>
	<script
		src="<c:url value="/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.min.js" />"></script>
	<script
		src="<c:url value="/webjars/jquery-validation/1.12.0/jquery.validate.min.js" />"></script>
</body>
</html>