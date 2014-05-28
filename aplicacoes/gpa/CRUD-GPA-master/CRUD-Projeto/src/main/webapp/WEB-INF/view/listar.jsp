<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="<c:url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />"
	rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listagem de Projetos</title>
</head>
<body>

	<ul class="pager">
		<li class="previous"><a href="index">&larr; Voltar para
				Início</a></li>
	</ul>


	<div class="container">
		<div class="panel panel-default">
			<!-- Default panel contents -->

			<div class="panel-heading" align="right">
				<a href="cadastro"><button class="btn btn-primary">
						Cadastrar Projeto <span class="glyphicon glyphicon-plus"></span>
					</button></a>
			</div>
			<div class="panel-heading" align="left">
				<h4">Lista de Projeto</h4>
			</div>

			<!-- Table -->
			<table class="table" id="table">
				<thead>
					<tr>
						<th id="teste">Identificador</th>
						<th>Nome</th>
						<th>Status</th>
						<th id="acoes">Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="projeto" items="${projetos}">
						<tr class="linha">
							<td>${projeto.id}</td>
							<td><a href="<c:url value="/${projeto.id}/informacoesProjeto" ></c:url>">${projeto.nome}</a></td>
							<td class="status">${projeto.status}</td>
							<td class="acoes"><a id="editar"
								href="<c:url value="/${projeto.id}/editarProjeto" ></c:url>"><button  glyphicon glyphicon-trash
										class="botaoBloqueado btn btn-primary">Editar <span class="glyphicon glyphicon-cog"></span> </button></a> <a
								id="excluir"
								href="<c:url value="/${projeto.id}/excluirProjeto" ></c:url>"><button
										id="deletar" class="botaoBloqueado btn btn-primary"
										name="deletar">Excluir <span class="glyphicon glyphicon-trash"></span></button></a> <a id="submeter"
								href="<c:url value="/${projeto.id}/submeterProjeto" ></c:url>"
								onclick="submeter(${projeto.id});"><button
										class="botaoBloqueado btn btn-primary">Submeter <span class="glyphicon glyphicon-open"></span></button></a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
	


	<script src="<c:url value="/webjars/jquery/2.1.0/jquery.min.js" />"></script>
	<script	src="<c:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/funcoes.js" />"></script>
</body>
</html>