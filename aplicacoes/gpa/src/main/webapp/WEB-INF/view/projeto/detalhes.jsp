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

<body onload="esconderComentarioSeVazio()">
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
						<td class="head">Avaliação:</td>

						<c:if test="${not empty projeto.avaliacao}">					
							<td class="content"> <fmt:formatDate pattern="dd-MM-yyyy"
									value="${projeto.avaliacao }" /> às <fmt:formatDate
									pattern="HH:mm" value="${projeto.avaliacao }" /></td> 
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
						<td class="head">Carga Horária:</td>
						<td class="content">${projeto.cargaHoraria}</td>
					</tr>
					<tr>
						<td class="head">Valor da Bolsa:</td>
						<td class="content">${projeto.valorDaBolsa}</td>
					</tr>
					<tr>
						<td class="head" valign="top">Participantes:</td>
							<c:forEach items="${projeto.participantes}" var="participante">
							
							<td class="content">	<a href="<c:url value="/projeto/${participante.id}/detalhesParticipante" ></c:url>">${participante.nome}</a></td>
							
		           		    </c:forEach>
					</tr>
					<tr>
						<td class="head">Atividades:</td>
						<td class="content">${projeto.atividades }</td>
					</tr>

					<tr>
						<td class="head" valign="top">Arquivos:</td>
						<c:if test="${not empty projeto.documentos }">
								<table id="table-anexos" class="table table-striped">
									<thead>
										<tr>
											<th data-column-id="nome" data-order="desc">Arquivo</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${projeto.documentos }" var="documento">
			                    			<tr>
										        <td>
										            <a href="<c:url value="/documento/${documento.id }" />">${documento.nome }</a>
										        </td>
										    </tr>	
			                    		</c:forEach>
									</tbody>
								</table>
							</c:if>
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
							value="${projeto.id}"> <input type="hidden" id="pessoa"
							name="pessoa" value="${usuario.id}"> <input
							type="hidden" id="pessoa_nome" name="pessoa"
							value="${usuario.nome}"> <label class="control-label"
							for="textocomentarioInput"><h3>Novo Comentário</h3></label>
						<div class="form-group">
							<div class="input-group">
								<textarea id="textocomentarioInput" name="texto"
									class="form-control" placeholder="Comentário"></textarea>
							</div>
						</div>
						<br> <input name="botao" id="botaoEnviarComentario" type="submit"
							class="btn btn-primary" value="Enviar" /> <a
							href="<c:url value="/projeto/index"></c:url>"
							class="btn btn-default">Voltar</a>
					</form>
				</div>


			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp" />


	

</body>
</html>
