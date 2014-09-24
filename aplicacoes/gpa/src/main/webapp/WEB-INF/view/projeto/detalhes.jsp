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
<body onload="verificarSeExisteUlNaPagina()">
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
						<td class="head">Bolsas:</td>
						<td class="content">${projeto.quantidadeBolsa }</td>
					</tr>
					<tr>
						<td class="head">Local:</td>
						<td class="content">${projeto.local }</td>
					</tr>
					<tr>
						<td class="head">Participantes:</td>
						<td class="content">${projeto.participantes }</td>
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
				<div>
					<div class="container ">
						<h3 id="headComentarios">Comentários do Projeto</h3>
						<ul id="comentarioList" class="ca-menu">
							<c:forEach var="comentario" items="${projeto.comentarios}">
								<li id="novoComentario" class="well">
									<div class="nome_pessoa">${comentario.pessoa.nome}</div>
									<div class="corpo_texto">${comentario.texto}</div>
									<div class="formatacao_data">
										<fmt:formatDate pattern="dd/MM/yyyy hh:mm"
											value="${comentario.data}" />
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>

				</div>

				<div class="row text-center">
					<form id="formularioCadastroComentario" role="form" method="POST"
						class="form-horizontal">
						<input type="hidden" id="projeto" name="projeto"
							value="${projeto.id}"> 							
							 <input type="hidden"
							id="pessoa_nome" name="pessoa" value="${pessoa.nome}"> 
							<input type="hidden" id="pessoa"
							name="pessoa" value="${pessoa.id}">
							<label
							class="control-label" for="textocomentarioInput">
							<h3>Novo Comentário</h3>
						</label>
						<div class="form-group">
							<div class="input-group">
								<textarea id="textocomentarioInput" name="texto"
									class="form-control" placeholder="Comentário"></textarea>
							</div>
						</div>
						<br> <input name="botao" id="botaoEnviarComentario"
							type="submit" class="btn btn-primary" value="Enviar" /> <a
							href="<c:url value="/projeto/index"></c:url>"
							class="btn btn-default">Voltar</a>
					</form>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="../modulos/footer.jsp" />
	<script
		src="<c:url value="/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.min.js" />"></script>
</body>
</html>