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
	<title>Editar Projeto</title>
</head>

<body>
	<jsp:include page="../modulos/header.jsp" />
	
	<div class="container">
		<c:if test="${action == 'submeter'}">
			<div class="alert alert-warning alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<c:out value="É necessário preencher todas as informações do projeto para submetê-lo."></c:out>
			</div>
		</c:if>
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<c:if test="${action == 'editar'}">
					<c:set var="url" value="/projeto/${projeto.id}/editar"></c:set>
					<h2>Editar - ${projeto.nome }</h2>
				</c:if>
				<c:if test="${action == 'submeter'}">
					<c:set var="url" value="/projeto/submeter"></c:set>
					<h2>Submeter Projeto</h2>
				</c:if>
				
				<form:form id="editarForm" commandName="projeto" servletRelativeAction="${url}" enctype="multipart/form-data" cssClass="form-horizontal" method="POST">
					<input type="hidden" name="id" value="${projeto.id }"/>
					<input type="hidden" id="valorDaBolsa" name="valorDaBolsa" value="${projeto.valorDaBolsa }"/>
					
					<div class="form-group form-item">
						<label for="nome" class="col-sm-2 control-label"><span class="required">*</span> Nome:</label>
						<div class="col-sm-10">
							<form:input id="nome" path="nome" name="nome" cssClass="form-control" placeholder="Nome do projeto" required="required" />
							<div class="error-validation">
								<form:errors path="nome"></form:errors>
							</div>
							<c:if test="${not empty error_nome}">
								<div class="error-validation">
									<span>${error_nome}</span>
								</div>
							</c:if>
						</div>
					</div>
					
					<div class="form-group form-item">
						<label for="descricao" class="col-sm-2 control-label"><span class="required">*</span> Descrição:</label>
						<div class="col-sm-10">
							<form:textarea id="descricao" path="descricao" name="descricao" class="form-control" rows="5" placeholder="Descrição"  required="required"></form:textarea>
							<div class="error-validation">
								<form:errors path="descricao"></form:errors>
							</div>
							<c:if test="${not empty error_descricao}">
								<div class="error-validation">
									<span>${error_descricao}</span>
								</div>
							</c:if>
						</div>
						
					</div>

					<div class="form-group">
						<div class="form-item">
							<label for="inicio" class="col-sm-2 control-label"><span class="required">*</span> Início:</label>
							<div class="col-sm-2">
								<form:input id="inicio" type="text" path="inicio" name="inicio" cssClass="form-control data" placeholder="Data de Início" required="required"/>
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
							<label for="termino" class="col-sm-2 control-label"><span class="required">*</span> Término:</label>
							<div class="col-sm-2">
								<form:input id="termino" type="text" path="termino" name="termnino" cssClass="form-control data" placeholder="Data de Término" required="required"/>
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
							<label for="bolsas" class="col-sm-2 control-label"><span class="required">*</span> Número de bolsas:</label>
							<div class="col-sm-2">
								<form:input id="bolsas" type="number" min="0" name="quantidadeBolsa" placeholder="0" path="quantidadeBolsa" cssClass="form-control" required="required"/>
								<c:if test="${not empty error_quantidadeBolsa}">
									<div class="error-validation">
										<span>${error_quantidadeBolsa}</span>
									</div>
								</c:if>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="form-item">
							<label for="cargaHoraria" class="col-sm-2 control-label"><span class="required">*</span> Carga Horária:</label>
							<div class="col-sm-2">
								<form:input id="cargaHoraria" type="number" min="1" placeholder="0" name="cargaHoraria" path="cargaHoraria" cssClass="form-control" required="required"/>
								<div class="error-validation">
									<form:errors path="cargaHoraria"></form:errors>
								</div>
							</div>
						</div>
						<div class="form-item">
							<label for="bolsa" class="col-sm-2 control-label"><span class="required">*</span> Valor da bolsa:</label>
							<div class="col-sm-2">
								<input id="bolsa" name="bolsa" placeholder="R$ 0,00" class="form-control" value="${projeto.valorDaBolsa}" required="required"/>
								<div class="error-validation">
									<form:errors path="valorDaBolsa"></form:errors>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="local" class="col-sm-2 control-label">Local:</label>
						<div class="col-sm-10">
							<form:input id="local" path="local" cssClass="form-control" placeholder="Local do projeto"/>
							<c:if test="${not empty error_local}">
								<div class="error-validation">
									<span>${error_local}</span>
								</div>
							</c:if>
						</div>
					</div>

					<div class="form-group" id="containerParticipantes">
						<label id="labelParticipante" for="participantes" class="col-sm-2 control-label">Participantes:</label>
						<div class="col-sm-10">
							<form:input path="" name="participanteEscolhido" id="participanteEscolhido" cssClass="form-control" placeholder="Participantes do projeto" />
							<button type="button" name="addParticipante" id="addParticipante">Adicionar Participante</button>

							<c:if test="${not empty error_participantes}">
								<div class="error-validation">
									<span>${error_participantes}</span>
								</div>
							</c:if>

							<datalist id="listaParticipantes">
								<c:forEach items="${participantes}" var="participante">
									<option value="${participante.nome}"
										label="CPF : ${participante.cpf}">
								</c:forEach>
							</datalist>
							<div id="listaParticipantesCadastrados">
								<c:forEach items="${projeto.participantes}" var="participante">
									<label class="participanteSelecionado" for="participanteSelecionado">${participante.nome}</label>
									<input type="checkbox" class="participanteSelecionado" id="participanteSelecionado" name="participanteSelecionado" value="${participante.nome}" checked="checked"> ,
					
		            			</c:forEach>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="atividades" class="col-sm-2 control-label">Atividades:</label>
						<div class="col-sm-10">
							<form:textarea id="atividades" path="atividades" name="atividades" class="form-control" rows="5" placeholder="Atividades"  ></form:textarea>
							<c:if test="${not empty error_atividades}">
								<div class="error-validation">
									<span>${error_atividades}</span>
								</div>
							</c:if>
						</div>
					</div>
					
					<div class="form-group">
						<label for="atividades" class="col-sm-2 control-label">Arquivos:</label>
						<div class="col-sm-10 files">
							<input class="btn btn-success" type="file" name="file" title="Adicionar arquivos..." multiple="multiple">
		                    <table id="file-upload" role="presentation" class="table table-striped">
		                    	<tbody class="files">
		                    		<c:forEach items="${projeto.documentos }" var="documento">
		                    			<tr class="template-upload fade in">
									        <td>
									            <a href="<c:url value="/documento/${documento.id }" />">${documento.nomeOriginal }</a>
									            <strong class="error text-danger"></strong>
									        </td>
									        <td>
								                <a id="${documento.id}" href="#" class="delete-document">
													<button type="button" class="btn btn-danger">Excluir <span class="glyphicon glyphicon-trash"></span></button>
												</a>
									        </td>
									    </tr>	
		                    		</c:forEach>
		                    	</tbody>
		                    </table>

		                    <c:if test="${not empty error_documento}">
								<div class="error-validation">
									<span>${error_documento}</span>
								</div>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-2">
							<span class="campo-obrigatorio"><span class="required">*</span> Campos Obrigatórios</span>
						</div>
					</div>
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Salvar" />
						<a href="<c:url value="/projeto/index"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp" />

</body>
</html>
