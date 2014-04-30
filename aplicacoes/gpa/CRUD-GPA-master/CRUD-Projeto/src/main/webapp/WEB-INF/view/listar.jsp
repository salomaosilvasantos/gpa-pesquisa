<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="<c:url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />"
	rel="stylesheet" />
<script src="<c:url value="/webjars/jquery/2.1.0/jquery.min.js" />"></script>	
<script src="<c:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js" />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listagem de Projetos</title>
</head>
<body>

	<ul class="pager">
		<li class="previous"><a href="home">&larr; Voltar para Início</a></li>
	</ul>
	<div class="container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">Listagem de Projetos</div>

			<!-- Table -->
			<table class="table">
		<thead>
					<tr>
						<th>Identificador do Projeto</th>
						<th>Nome do Projeto</th>
						<th>Status do Projeto</th>
						<th>Ações</th>

					</tr>
					</thead>		
		<tbody>
			<c:forEach var="projeto" items="${projetos}">
						<tr>
							<td>${projeto.id}</td>
							<td>${projeto.nome}</td>
					<td class="status">${projeto.status}</td>	
					<td>			 				
					<a href="<c:url value="/${projeto.id}/editarProjeto" ></c:url>"><button class="botaoBloqueado btn btn-primary">Editar</button></a>
					<a href="<c:url value="/${projeto.id}/excluirProjeto" ></c:url>"><button class="botaoBloqueado btn btn-primary">Excluir</button></a>
					<a href="<c:url value="/${projeto.id}/submeterProjeto"></c:url>"><button class="botaoBloqueado btn btn-primary">Submeter</button></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table>
	
	<script type="text/javascript">
		$(document).ready(function(){
			var status = $('.status').text();
			if(status == 'SUBMETIDO'){
				$(".botaoBloqueado").prop("disabled", true);
			}
		});
	</script>
	
	
</body>
</html>