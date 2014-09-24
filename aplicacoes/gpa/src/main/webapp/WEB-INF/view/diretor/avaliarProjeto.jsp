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
				<h2>Avaliar Projeto</h2>

				<form:form
					servletRelativeAction="/projeto/${projeto.id}/avaliarProjeto"
					commandName="projeto" enctype="multipart/form-data" method="POST"
					cssClass="form-horizontal">

					<div class="form-group">
						<label for="status" class="col-sm-2 control-label">Status</label>
						<div class="col-sm-10 files">
							<select name="opcoesAvaliacao">
								<option id="aprovado" name="aprovado" class="form-control">Aprovado</option>
								<option id="aprovado-com-restricao"
									name="aprovado-com-restricao" class="form-control">Aprovado
									com restrição</option>
								<option id="reprovado" name="reprovado" class="form-control">Reprovado</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="observacao" class="col-sm-2 control-label">Observação:</label>
						<div class="col-sm-10">
							<textarea id="observacao" name="observacao" class="form-control"
								rows="8" placeholder="Observação"></textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="ata" class="col-sm-2 control-label">Anexar Ata:</label>
						<div class="col-sm-10 files">
							<input class="btn btn-success" type="file" name="file"
								title="Anexar ata..." multiple="multiple">
						</div>
					</div>
					
					<div class="form-group">
						<label for="oficio" class="col-sm-2 control-label">Anexar Ofício:</label>
						<div class="col-sm-10 files">
							<input class="btn btn-success" type="file" name="file"
								title="Anexar oficio..." multiple="multiple">
						</div>
					</div>


					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary"
							value="Emitir" /> <a
							href="<c:url value="/projeto/index"></c:url>"
							class="btn btn-default">Cancelar</a>
					</div>
				</form:form>
			</div>

		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp"></jsp:include>

</body>



</html>