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
	<title>Emitir Parecer</title>
</head>

<body>
	<jsp:include page="../modulos/header.jsp" />

	<div class="container">
		<div class="emitirParecer" align="center">
			<div class="form" align="center">
				<h2>Emitir Parecer - ${projeto.nome }</h2>
				<form:form id="emitirParecerForm" servletRelativeAction="/projeto/${projeto.id}/emitirParecer" commandName="parecer" method="POST" cssClass="form-horizontal">
					<div class="form-group form-item">
						<label for="autor" class="col-sm-2 control-label">Autor:</label>
						<div class="col-sm-10">
							<label class="col-sm-2 control-label">${projeto.autor.nome }</label>
						</div>
					</div>
					<div class="form-group form-item">
						<label for="observacao" class="col-sm-2 control-label">Observação do Diretor:</label>
						<div class="col-sm-10">
							<label class="col-sm-10 control-label">${projeto.parecer.observacao }</label>
						</div>
					</div>
					<div class="form-group form-item">
						<label for="posicionamento" class="col-sm-2 control-label"><span class="required">*</span> Posicionamento:</label>
						<div class="col-sm-10">
							<select id="posicionamento" name="posicionamento" class="form-control">
								<c:forEach items="${posicionamento}" var="pos">
									<option value="${pos}">${pos.descricao}</option>
								</c:forEach>
							</select>
						</div>
					</div>
		
					<div class="form-group">
						<div class="form-item">
							<label for="parecer" class="col-sm-2 control-label"><span class="required">*</span> Parecer:</label>
							<div class="col-sm-10">
								<textarea id="parecer" name="parecer" class="form-control" rows="8" placeholder="Parecer" required="required"></textarea>
							</div>
							<c:if test="${not empty erro_parecer}">
								<div class="error-validation">
									<span>${erro_parecer}</span>
								</div>
							</c:if>
						</div>
					</div>
					
					<div class="form-group">
						<label for="anexo" class="col-sm-2 control-label">Anexo:</label>
						<div class="col-sm-10 files"></div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-2">
							<span class="campo-obrigatorio"><span class="required">*</span> Campos obrigatórios</span>
						</div>
					</div>

					<div class="controls">
						<input name="emitir" type="submit" class="btn btn-primary" value="Salvar" />
						<a href="<c:url value="/projeto/index"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
			</form:form>
			</div>

		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp"></jsp:include>

</body>



</html>