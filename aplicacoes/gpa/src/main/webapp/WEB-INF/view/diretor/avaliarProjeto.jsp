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
<title>Avaliar Projeto</title>
</head>

<body>
	<jsp:include page="../modulos/header.jsp" />

	<div class="container">
		<div class="emitirParecer" align="center">
			<div class="form" align="center">
				<h2>Avaliar - ${projeto.nome }</h2>
				<form:form id="avaliarProjetoForm" servletRelativeAction="/projeto/diretor/${projeto.id}/avaliar" method="POST" cssClass="form-horizontal">
					<div class="form-group form-item">
						<label for="avaliacao" class="col-sm-2 control-label">Status:</label>
						<div class="col-sm-10 files">
							<select id="avaliacao" name="avaliacao" class="form-control">
								<option value="APROVADO">APROVADO</option>
								<option value="APROVADO_COM_RESTRICAO">APROVADO COM RESTRIÇÃO</option>
								<option value="REPROVADO">REPROVADO</option>
							</select>
						</div>
					</div>

					<!-- <div class="form-group">
						<label for="ata" class="col-sm-2 control-label">Anexar Ata:</label>
						<div class="col-sm-10 files">
							<input class="btn btn-success" type="file" name="file" title="Anexar ata..." multiple="multiple">
						</div>
					</div>
					
					<div class="form-group">
						<label for="oficio" class="col-sm-2 control-label">Anexar Ofício:</label>
						<div class="col-sm-10 files">
							<input class="btn btn-success" type="file" name="file" title="Anexar oficio..." multiple="multiple">
						</div>
					</div> -->


					<div class="controls">
						<input name="salvar" type="submit" class="btn btn-primary" value="Salvar" />
						<a href="<c:url value="/projeto/index"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp"></jsp:include>

</body>



</html>