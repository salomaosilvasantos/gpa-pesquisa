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
<jsp:include page="../modulos/header-estrutura.jsp" />
<title>Cadastro de Projetos</title>
</head>
<body>

	<jsp:include page="../modulos/header.jsp" />

	<div class="container">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Novo Projeto</h2>
				<form:form id="adicionarProjetoForm" role="form"
					commandName="projeto" servletRelativeAction="/projeto/cadastrar"
					method="POST" cssClass="form-horizontal">

					<div class="form-group">
						<label for="nome" class="col-sm-2 control-label">Nome:</label>
						<div class="col-sm-10">
							<form:input id="nome" path="nome" onchange="TrimNome()"
								cssClass="form-control" placeholder="Nome do projeto" />
							<div class="error-validation">
								<form:errors path="nome"></form:errors>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="descricao" class="col-sm-2 control-label">Descrição:</label>
						<div class="col-sm-10">
							<form:textarea id="descricao" path="descricao"
								onchange="TrimDescricao()" class="form-control" rows="5"
								placeholder="Descrição"></form:textarea>
							<div class="error-validation">
								<form:errors path="descricao"></form:errors>
							</div>
						</div>

					</div>

					<div class="form-group">
						<label for="inicio" class="col-sm-2 control-label">Início:</label>
						<div class="col-sm-2">
							<form:input id="inicio" type="text" path="inicio"
								cssClass="form-control data" placeholder="Data de Início" />
							<div class="error-validation">
								<form:errors path="inicio"></form:errors>
							</div>
							<c:if test="${not empty error_inicio}">
								<div class="error-validation">
									<span>${error_inicio}</span>
								</div>
							</c:if>
						</div>

						<label for="termino" class="col-sm-2 control-label">Término:</label>
						<div class="col-sm-2">
							<form:input id="termino" type="text" path="termino"
								cssClass="form-control data" placeholder="Data de Término" />
							<div class="error-validation">
								<form:errors path="termino"></form:errors>
							</div>
							<c:if test="${not empty error_termino}">
								<div class="error-validation">
									<span>${error_termino}</span>
								</div>
							</c:if>
						</div>

						<label for="bolsas" class="col-sm-2 control-label">Número
							de bolsas:</label>
						<div class="col-sm-2">
							<form:input id="bolsas" type="number" min="0" placeholder="0"
								path="quantidadeBolsa" cssClass="form-control" />
						</div>
					</div>

					<div class="form-group">
						<label for="cargaHoraria" class="col-sm-2 control-label">Carga
							Horária:</label>
						<div class="col-sm-2">
							<form:input id="cargaHoraria" type="number" min="1"
								placeholder="45" path="cargaHoraria" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">

						<label for="valorDaBolsa" class="col-sm-2 control-label">Valor
							da bolsa:</label>
						<div class="col-sm-2">
							<form:input id="valorDaBolsa" type="number" min="0"
								placeholder="0" path="valorDaBolsa" cssClass="form-control" />
						</div>
					</div>

					<div class="form-group">
						<label for="local" class="col-sm-2 control-label">Local:</label>
						<div class="col-sm-10">
							<form:input id="local" path="local" cssClass="form-control"
								placeholder="Local do projeto" />
						</div>
					</div>

					<div class="form-group">
						<label for="atividades" class="col-sm-2 control-label">Atividades:</label>
						<div class="col-sm-10">
							<form:textarea id="atividades" path="atividades"
								name="atividades" class="form-control" rows="5"
								placeholder="Atividades"></form:textarea>
						</div>
					</div>

					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary"
							value="Cadastrar" /> <a
							href="<c:url value="/projeto/index"></c:url>"
							class="btn btn-default">Cancelar</a>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp" />

</body>

<script>
	function TrimNome() {
		var nome = document.getElementById('nome');
		nome.value = nome.value.trim();
	}
	function TrimDescricao() {
		var descricao = document.getElementById('descricao');
		descricao.value = descricao.value.trim();
	}
</script>

<script type="text/javascript">
	$(document).ready(function($) {
	}(jQuery));
</script>

</html>
