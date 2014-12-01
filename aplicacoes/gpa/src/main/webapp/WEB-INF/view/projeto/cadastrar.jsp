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
				<form:form id="adicionarProjetoForm" role="form" commandName="projeto" servletRelativeAction="/projeto/cadastrar"
					method="POST" cssClass="form-horizontal">

					<div class="form-group form-item">
						<label for="nome" class="col-sm-2 control-label">Nome:</label>
						<div class="col-sm-10">
							<form:input id="nome" name="nome" path="nome" cssClass="form-control" placeholder="Nome do projeto" required="required"/>
							<div class="error-validation">
								<form:errors path="nome"></form:errors>
							</div>
						</div>
					</div>

					<div class="form-group form-item">
						<label for="descricao" class="col-sm-2 control-label">Descrição:</label>
						<div class="col-sm-10">
							<form:textarea id="descricao" path="descricao" class="form-control" rows="5" placeholder="Descrição" name="descricao" required="required"/>
							<div class="error-validation">
								<form:errors path="descricao"></form:errors>
							</div>
						</div>

					</div>

					<div class="form-group">
						<div class="form-item">
							<label for="inicio" class="col-sm-2 control-label">Início:</label>
							<div class="col-sm-2">
								<form:input id="inicio" type="text" path="inicio" cssClass="form-control data" placeholder="Data de início" />
								<div class="error-validation">
									<form:errors path="inicio"></form:errors>
								</div>
								<c:if test="${not empty error_inicio}">
									<div class="error-validation">
										<span>${error_inicio}</span>
									</div>
								</c:if>
							</div>
						</div>

						<div class="form-item">
							<label for="termino" class="col-sm-2 control-label">Término:</label>
							<div class="col-sm-2">
								<form:input id="termino" type="text" path="termino" cssClass="form-control data" placeholder="Data de término" />
								<div class="error-validation">
									<form:errors path="termino"></form:errors>
								</div>
								<c:if test="${not empty error_termino}">
									<div class="error-validation">
										<span>${error_termino}</span>
									</div>
								</c:if>
							</div>
						</div>

						<div class="form-item">
							<label for="bolsas" class="col-sm-2 control-label">Número de bolsas:</label>
							<div class="col-sm-2" >
								<form:input id="bolsas" name="quantidadeBolsa" type="number" placeholder="0" path="quantidadeBolsa" cssClass="form-control" min="0"/>
								<div class="error-validation">
									<form:errors path="quantidadeBolsa"></form:errors>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="form-item">
							<label for="cargaHoraria" class="col-sm-2 control-label">Carga horária:</label>
							<div class="col-sm-2">
								<form:input id="cargaHoraria" name="cargaHoraria" type="number" placeholder="0" path="cargaHoraria" cssClass="form-control" min="0"/>
								<div class="error-validation">
									<form:errors path="cargaHoraria"></form:errors>
								</div>
							</div>
						</div>
						
						<div class="form-item">
							<label for="valorDaBolsa" class="col-sm-2 control-label">Valor da bolsa:</label>
							<div class="col-sm-2">
								<form:input id="valorDaBolsa" name="valorDaBolsa" placeholder="R$ 0,00" path="valorDaBolsa" cssClass="form-control" />
							</div>
						</div>
					</div>
					<div class="form-group form-item">
						<label for="local" class="col-sm-2 control-label">Local:</label>
						<div class="col-sm-10">
							<form:input id="local" path="local" cssClass="form-control" placeholder="Local do projeto" />
						</div>
					</div>

					<div class="form-group form-item">
						<label for="atividades" class="col-sm-2 control-label">Atividades:</label>
						<div class="col-sm-10">
							<form:textarea id="atividades" path="atividades" name="atividades" class="form-control" rows="5" placeholder="Atividades"></form:textarea>
						</div>
					</div>

					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Cadastrar" />
						<a href="<c:url value="/projeto/index"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp" />

</body>
</html>
