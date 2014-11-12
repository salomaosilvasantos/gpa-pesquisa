<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<jsp:include page="../modulos/header-estrutura.jsp" />
<title>Projetos</title>
</head>
<body>
	<jsp:include page="../modulos/header.jsp" />

	<div class="container">
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<c:out value="${erro}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<c:out value="${info}"></c:out>
			</div>
		</c:if>
		<div align="right" style="margin-bottom: 20px;">
			<a href="<c:url value="/projeto/cadastrar" ></c:url>">
				<button class="btn btn-primary">
					Novo Projeto <span class="glyphicon glyphicon-plus"></span>
				</button>
			</a>
		</div>

		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#meus-projetos" role="tab"
				data-toggle="tab">Meus Projetos</a></li>
			<li><a href="#projetos-submetidos" role="tab" data-toggle="tab">Projetos
					Submetidos</a></li>
			<li><a href="#projetos-avaliados" role="tab" data-toggle="tab">Projetos
					Avaliados</a></li>
		</ul>

		<div class="tab-content">

			<!-- Meus Projetos -->
			<div class="tab-pane active" id="meus-projetos">
				<c:if test="${empty projetos}">
					<div class="alert alert-warning" role="alert">Não há projetos
						cadastrados.</div>
				</c:if>
				<c:if test="${not empty projetos}">
					<div class="panel panel-default">
						<div class="panel-heading" align="center">
							<h4>Meus Projetos</h4>
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
										<td>${projeto.codigo}</td>
										<td><a
											href="<c:url value="/projeto/${projeto.id}/detalhes" ></c:url>">${projeto.nome}</a></td>
										<td class="status">${projeto.status.descricao}</td>
										<td><c:if test="${projeto.status == 'NOVO'}">
												<a id="submeter" data-toggle="modal"
													data-target="#confirm-submit" href="#"
													data-href="<c:url value="/projeto/${projeto.id}/submeter" ></c:url>">
													<button class="btn btn-primary">
														Submeter <span class="glyphicon glyphicon-upload"></span>
													</button>
												</a>

												<a id="editar"
													href="<c:url value="/projeto/${projeto.id}/editar" ></c:url>">
													<button class="btn btn-info">
														Editar <span class="glyphicon glyphicon-pencil"></span>
													</button>
												</a>

												<a id="excluir" data-toggle="modal"
													data-target="#confirm-delete" href="#"
													data-href="<c:url value="/projeto/${projeto.id}/excluir" ></c:url>">
													<button class="btn btn-danger">
														Excluir <span class="glyphicon glyphicon-trash"></span>
													</button>
												</a>
											</c:if> <sec:authorize ifAnyGranted="ROLE_DIRETOR">
												<c:if test="${projeto.status == 'SUBMETIDO'}">
													<a id="atribuirParecerista"
														href="<c:url value="/projeto/diretor/${projeto.id}/atribuirParecerista" ></c:url>">
														<button class="btn btn-primary">
															Atribuir Parecerista <span
																class="glyphicon glyphicon-user"></span>
														</button>
													</a>
												</c:if>
											</sec:authorize> <c:if test="${projeto.status == 'AGUARDANDO_AVALIACAO'}">
												<a id="verParecer" data-toggle="modal"
													href="<c:url value="/projeto/${projeto.id}/verParecer" ></c:url>">
													<button class="btn btn-primary">
														Ver Parecer <span class="glyphicon glyphicon-upload"></span>
													</button>
												</a>

												<a id="comentario"
													href="<c:url value="/projeto/${projeto.id}/detalhes" ></c:url>">
													<button class="btn btn-info">
														Comentario <span class="glyphicon glyphicon-pencil"></span>
													</button>
												</a>

												<a id="avaliarProjeto" data-toggle="modal"
													href="<c:url value="/projeto/${projeto.id}/avaliarProjeto" ></c:url>">
													<button class="btn btn-sucess">

														Avaliar Projeto <span class="glyphicon glyphicon-trash"></span>
													</button>
												</a>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>

			<!-- Projetos Submetidos -->
			<div class="tab-pane" id="projetos-submetidos">
				<c:if test="${empty projetosSubmetidos}">
					<div class="alert alert-warning" role="alert">Não há projetos
						submetidos.</div>
				</c:if>
				<c:if test="${not empty projetosSubmetidos}">
					<div class="panel panel-default">
						<div class="panel-heading" align="center">
							<h4>Projetos Submetidos</h4>
						</div>

						<!-- Table -->
						<table class="table" id="table">
							<thead>
								<tr>
									<th id="teste">Identificador</th>
									<th>Nome</th>
									<th>Autor</th>
									<th>Status</th>

									<th>Atribuição</th>
									<th>Prazo</th>

									<th id="acoes">Ações</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="projeto" items="${projetosSubmetidos}">
									<tr class="linha">
										<td>${projeto.codigo}</td>
										<td><a
											href="<c:url value="/projeto/${projeto.id}/detalhes" ></c:url>">${projeto.nome}</a></td>
										<td>${projeto.autor.nome}</td>
										<td class="status">${projeto.status.descricao}</td>

										<td>${projeto.pareceres[0].usuario.nome}</td>

										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${projeto.pareceres[0].prazo}" /></td>

										<td><c:if test="${projeto.status == 'SUBMETIDO'}">
												<a id="atribuirParecerista"
													href="<c:url value="/projeto/diretor/${projeto.id}/atribuirParecerista" ></c:url>">
													<button class="btn btn-primary">
														Atribuir Parecerista <span
															class="glyphicon glyphicon-user"></span>
													</button>
												</a>
											</c:if> <c:if test="${projeto.status == 'AGUARDANDO_AVALIACAO'}">
												<a id="verParecer" data-toggle="modal"
													href="<c:url value="/projeto/${projeto.id}/verParecer" ></c:url>">
													<button class="btn btn-primary">
														Ver Parecer <span class="glyphicon glyphicon-upload"></span>
													</button>
												</a>

												<a id="comentario"
													href="<c:url value="/projeto/${projeto.id}/detalhes" ></c:url>">
													<button class="btn btn-info">
														Comentario <span class="glyphicon glyphicon-pencil"></span>
													</button>
												</a>

												<a id="avaliarProjeto" data-toggle="modal"
													href="<c:url value="/projeto/${projeto.id}/avaliarProjeto" ></c:url>">
													<button class="btn btn-danger">

														Avaliar Projeto <span class="glyphicon glyphicon-ok"></span>
													</button>
												</a>
											</c:if></td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
			<!-- Projetos Avaliados -->
			<div class="tab-pane" id="projetos-avaliados">
				<c:if test="${empty projetosAvaliados}">
					<div class="alert alert-warning" role="alert">Não há projetos
						Avaliados.</div>
				</c:if>
				<c:if test="${not empty projetosAvaliados}">
					<div class="panel panel-default">
						<div class="panel-heading" align="center">
							<h4>Projetos Avaliados</h4>
						</div>
						<input type="hidden" name="parecerId" value="${parecerId}">
						<!-- Table -->
						<table class="table" id="table">
							<thead>
								<tr>
									<th id="teste">Identificador</th>
									<th>Nome</th>
									<th>Autor</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="projeto" items="${projetosAvaliados}">
									<tr class="linha">
										<td>${projeto.codigo}</td>
										<td><a
											href="<c:url value="/projeto/${projeto.id}/detalhes" ></c:url>">${projeto.nome}</a></td>
										<td>${projeto.autor.nome}</td>
										<td class="status">${projeto.status.descricao}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>

		</div>
	</div>

	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Excluir</div>
				<div class="modal-body">Tem certeza de que deseja excluir esse
					projeto?</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-danger">Excluir</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="confirm-submit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Submeter Projeto</div>
				<div class="modal-body">Tem certeza de que deseja submeter
					esse projeto?</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-primary">Submeter</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp" />

</body>
</html>