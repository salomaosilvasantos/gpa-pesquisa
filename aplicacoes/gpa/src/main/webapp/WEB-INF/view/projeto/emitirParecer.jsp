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
	<title>Atribuir Parecerista</title>
</head>

<body>
	<jsp:include page="../modulos/header.jsp" />

	<div class="container">
		<div class="emitirParecer" align="center">
			<div class="form" align="center">
				<h2>Emitir Parecer</h2>
				

				<form:form servletRelativeAction="/${projeto.id}/emitirParecer" method="POST" cssClass="form-horizontal">

<%-- 					<input type="hidden" name="projetoId" value="${projetoId}"> --%>
					
					<div class="form-group">
						<label for="parecer" class="col-sm-2 control-label">Parecer:</label>
						<div class="col-sm-10 files">
							<select>
								<option id="favoravel" name="favoravel" class="form-control">Favorável</option>
								<option id="nao-favoravel" name="nao-favoravel" class="form-control">Não Favorável</option>
							</select>
						</div>
					</div>
		
					<div class="form-group">
						<label for="comentario" class="col-sm-2 control-label">Comentário:</label>
						<div class="col-sm-10">
							<textarea id="comentario" name="comentario" class="form-control" rows="8" placeholder="Comentário" required="required" required title="Preencha"></textarea>
						</div>
					</div>
					
					<div class="form-group">
						<label for="comentario" class="col-sm-2 control-label">Anexo:</label>
						<div class="col-sm-10 files">
							<input class="btn btn-success" type="file" name="file" title="Anexar parecer..." multiple="multiple">					</div>


					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Emitir" />
						<a href="<c:url value="/projeto/index"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
			</form:form>
			</div>

		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp"></jsp:include>

</body>



</html>