<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<jsp:include page="../modulos/header-estrutura.jsp" />
	<title>Informações do Projeto</title>
</head>

<body onload="esconderComentarioSeVazio()">
	<div class="container">
		<jsp:include page="../modulos/header.jsp" />
		<div class="formulario detalhes">
			<input id="projetoId" type="hidden" value="${projeto.id }"/>
			<h2>${projeto.nome }</h2>
			<div class="form-group">
				<label class="col-sm-2 control-label field">Descrição:</label>
				<div class="col-sm-10 field-value">
					<label>${projeto.descricao }</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label field">Início:</label>
				<div class="col-sm-4 field-value">
					<c:if test="${empty projeto.inicio }">
						<label>-</label>
					</c:if>
					<label><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.inicio }" /></label>
				</div>
				<label class="col-sm-2 control-label field">Término:</label>
				<div class="col-sm-4 field-value">
					<c:if test="${empty projeto.termino }">
						<label>-</label>
					</c:if>
					<label><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.termino }" /></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label field">Data de submissão:</label>
				<div class="col-sm-4 field-value">
					<c:if test="${empty projeto.submissao }">
						<label>-</label>
					</c:if>
					<label><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${projeto.submissao }" /></label>
				</div>
				<label class="col-sm-2 control-label field">Data de avaliação:</label>
				<div class="col-sm-4 field-value">
					<c:if test="${empty projeto.avaliacao }">
						<label>-</label>
					</c:if>
					<label><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${projeto.avaliacao }" /></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label field">Carga horária:</label>
				<div class="col-sm-2 field-value">
					<c:if test="${empty projeto.cargaHoraria }">
						<label>-</label>
					</c:if>
					<label>${projeto.cargaHoraria }</label>
				</div>
				<label class="col-sm-2 control-label field">Bolsas:</label>
				<div class="col-sm-2 field-value">
					<c:if test="${empty projeto.quantidadeBolsa }">
						<label>-</label>
					</c:if>
					<label>${projeto.quantidadeBolsa }</label>
				</div>
				<label class="col-sm-2 control-label field">Valor da bolsa:</label>
				<div class="col-sm-2 field-value">
					<c:if test="${empty projeto.valorDaBolsa or projeto.valorDaBolsa == 0.0 }">
						<label>-</label>
					</c:if>
					<c:if test="${not empty projeto.valorDaBolsa and projeto.valorDaBolsa != 0.0 }">
						<label>${projeto.valorDaBolsa }</label>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label field">Local:</label>
				<div class="col-sm-10 field-value">
					<c:if test="${empty projeto.local }">
						<label>-</label>
					</c:if>
					<label>${projeto.local }</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label field">Atividades:</label>
				<div class="col-sm-10 field-value">
					<c:if test="${empty projeto.atividades }">
						<label>-</label>
					</c:if>
					<label>${projeto.atividades }</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label field">Participantes:</label>
				<div class="col-sm-10 field-value">
					<c:if test="${empty projeto.participantes }">
						<label>-</label>
					</c:if>
				</div>
				<c:forEach items="${projeto.participantes }" var="participante">
					<div class="col-sm-10 field-value">
						<label>${participante.nome }</label>
					</div>
				</c:forEach>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label field">Anexos:</label>
				<div class="col-sm-10 field-value">
					<c:if test="${empty projeto.documentos }">
						<label>-</label>
					</c:if>
				</div>
				<c:forEach items="${projeto.documentos }" var="documento">
					<div class="col-sm-10 field-value">
						<a href="<c:url value="/documento/${documento.id }" />">${documento.nome }</a>
					</div>
				</c:forEach>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12 field-value">
					<ul class="cbp_tmtimeline">
						<c:forEach items="${projeto.comentarios }" var="comentario">
							<li>
						        <time class="cbp_tmtime">
						        	<span><fmt:formatDate pattern="dd/MM/yyyy" value="${comentario.data }"/></span>
						        	<span><fmt:formatDate pattern="HH:mm" value="${comentario.data }" /></span>
						        </time>
						        <div class="cbp_tmlabel">
						            <h2>${comentario.autor.nome }</h2>
						            <p>${comentario.texto }</p>
						        </div>
						    </li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label field">Comentário:</label>
				<div class="col-sm-10 field-value">
					<textarea id="comentario" name="comentario" class="form-control" rows="5" placeholder="Comentário"></textarea>
				</div>
			</div>
			<div>
				<input id="comentar" name="comentar" type="submit" class="btn btn-primary" value="Enviar" />
			</div>
		</div>
	</div>
	<jsp:include page="../modulos/footer.jsp" />
</body>
</html>
