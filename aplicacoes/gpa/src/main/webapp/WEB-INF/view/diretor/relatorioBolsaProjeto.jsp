<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Bolsas por Projeto</title>
</head>


<body>
	<jsp:include page="../modulos/header.jsp" />

	<div class="container">

		<div class="row text-center">

			<form id="formularioCadastroComentario" role="form" method="POST"
				class="form-horizontal">
				<div class="form-group">
					<label for="inicio" class="col-sm-2 control-label">Início:</label>
					<div class="col-sm-2">
						<input id="inicio" type="text" class="form-control data"
							placeholder="Data de Início" />
					</div>
					<label for="termino" class="col-sm-2 control-label">Termino:</label>
					<div class="col-sm-2">
						<input id="termino" type="text" class="form-control data"
							placeholder="Data de Término" />
					</div>
				</div>

				<input name="botao" type="submit" class="btn btn-primary"
					value="Enviar" />
			</form>
		</div>

		
                <div class="tab-content">
                <h3>Quantidade de Projetos: <c:out value="${fn:length(projetos)}" /></h3>

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
                                    <th id="teste">Nome do Projeto </th>
                                    <th>Coordenador</th>
                                    <th>Quantidade de Participantes</th>
                                    <th>Valor da Bolsa</th>
                                    <th>Carga Horária</th>
                                    <th>Data de Início</th>
                                    <th>Data de Término</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="projeto" items="${projetos}">
                                    <tr class="linha">
                                        <td>${projeto.nome}</td>                                        
                                        <td>${projeto.autor.nome}</td>   
                                        <td>${projeto.quantidadeBolsa}</td>
                                        <td>${projeto.valorDaBolsa}</td>
                                        <td>${projeto.cargaHoraria}</td>
                                        <td>${projeto.inicio}</td>
                                        <td>${projeto.termino}</td>                                     
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>

	</div>

	<jsp:include page="../modulos/footer.jsp"></jsp:include>
</body>
</html>