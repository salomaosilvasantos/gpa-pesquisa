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
<title>Informações do Projeto</title>
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
				<h2>Detalhes do Projeto</h2>
				<table id="details">
					<tr>
						<td class="head">Nome:</td>
						<td class="content">${projeto.nome }</td>
					</tr>
					<tr>
						<td class="head">Descrição:</td>
						<td class="content">${projeto.descricao }</td>
					</tr>
					<tr>
						<td class="head">Início:</td>
						<td class="content"><fmt:formatDate pattern="dd-MM-yyyy"
								value="${projeto.inicio }" /></td>
					</tr>
					<tr>
						<td class="head">Término:</td>
						<td class="content"><fmt:formatDate pattern="dd-MM-yyyy"
								value="${projeto.termino }" /></td>
					</tr>

					<tr>
						<td class="head">Submissão:</td>

						<c:if test="${not empty projeto.submissao}">					
							<td class="content"> <fmt:formatDate pattern="dd-MM-yyyy"
									value="${projeto.submissao }" /> às <fmt:formatDate
									pattern="HH:mm" value="${projeto.submissao }" /></td> 
						</c:if>						

					</tr>
					<tr>
						<td class="head">Bolsas:</td>
						<td class="content">${projeto.quantidadeBolsa }</td>
					</tr>
					<tr>
						<td class="head">Local:</td>
						<td class="content">${projeto.local }</td>
					</tr>
					<tr>
						<td class="head" valign="top">Participantes:</td>
							<c:forEach items="${projeto.participantes}" var="participante">
								<td class="content">${participante.nome}</td>
		           		    </c:forEach>
					</tr>
					<tr>
						<td class="head">Atividades:</td>
						<td class="content">${projeto.atividades }</td>
					</tr>
					<tr>
						<td class="head" valign="top">Arquivos:</td>
						<c:forEach var="documento" items="${projeto.documentos}">
							<!-- <a href="<c:url value="/documento/${documento.id}"></c:url>">${documento.nomeOriginal}</a>-->
							<td class="content"><a
								href="<c:url value="/documento/${documento.id}"></c:url>">${documento.nomeOriginal}</a></td>
							<!-- <td class="content">${documento.nomeOriginal }</td>-->
						</c:forEach>
					</tr>
				</table>
				<div align="center" style="width: 50%">
					<h3>Comentários do Projeto</h3>
					<c:if test="${empty projeto.comentarios}">
						<div class="alert alert-warning" role="alert">Não há
							Comentários cadastrados.</div>
					</c:if>
					<c:if test="${not empty projeto.comentarios}">
						<c:forEach var="comentario" items="${projeto.comentarios}">
							<div class="well">
								<p>${comentario.usuario.nome}:
									<fmt:formatDate pattern="dd-MM-yyyy hh:mm"
										value="${comentario.data}" />
								</p>
							</div>
							<blockquote>
								<p>${comentario.texto}</p>
							</blockquote>
						</c:forEach>
					</c:if>
				</div>
				<div class="row text-center">
					<h3>Novo Comentário</h3>
					<form:form id="comentarProjeto" role="form"
						commandName="comentario"
						servletRelativeAction="/comentario/comentarProjeto" method="POST">
						<input type="hidden" id="projeto" name="projeto"
							value="${projeto.id}">
						<input type="hidden" id="usuario" name="usuario"
							value="${usuario.id}">
						<textarea name="textocomentario" id="textocomentario"
							required="required" placeholder="Comentário"
							oninvalid="this.setCustomValidity('Campo em branco não podem ser enviado')"
							style="max-width: 500px; max-height: 100px; min-width: 500px; min-height: 100px"
							draggable="true"></textarea>
						<br></br>
						<input name="submit" type="submit" class="btn btn-primary"
							value="Enviar" />
					</form:form>
				</div>
				<br></br>
				<div class="controls">
					<a href="<c:url value="/projeto/index"></c:url>"
						class="btn btn-default">Voltar</a>
				</div>
				<%-- <div class="form-group">
<label for="nome" class="col-sm-2 control-label">Nome: <span>${projeto.nome }</span> </label>
</div>
<div class="form-group">
<label for="descricao" class="col-sm-2 control-label">Descrição:</label>
<div class="col-sm-10">
<label>${projeto.descricao }</label>
</div>
</div>
<div class="form-group">
<label for="inicio" class="col-sm-2 control-label">Início:</label>
<div class="col-sm-2">
<label>${projeto.inicio }</label>
</div>
<label for="termino" class="col-sm-2 control-label">Término:</label>
<div class="col-sm-2">
<label>${projeto.termino }</label>
</div>
<label for="bolsas" class="col-sm-2 control-label">Número de bolsas:</label>
<div class="col-sm-2">
<label>${projeto.quantidadeBolsa }</label>
</div>
</div>
<div class="form-group">
<label for="local" class="col-sm-2 control-label">Local:</label>
<div class="col-sm-10">
<label>${projeto.local }</label>
</div>
</div>
<div class="form-group">
<label for="participantes" class="col-sm-2 control-label">Participantes:</label>
<div class="col-sm-10">
<label>${projeto.participantes }</label>
</div>
</div>
<div class="form-group">
<label for="atividades" class="col-sm-2 control-label">Atividades:</label>
<div class="col-sm-10">
<label>${projeto.atividades }</label>
</div>
</div> --%>
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
