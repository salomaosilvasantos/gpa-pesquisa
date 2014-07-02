<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Informações do Projeto</title>
		<link href="<c:url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/estilo.css" />" rel="stylesheet"/>
	
	</head>

	
<body>
	<header class="jumbotron" id="header-page">
		<div class="row">
			<div class="col-md-6">
				<img width="370" src="../resources/images/brasao-qxd.png" alt="Brasão UFC Quixadá">
			</div>
			<div class="col-md-6">
			</div>
		</div>
	</header>

	<div align="center">
		<ul class="pager">
			<li class="previous"><a href="<c:url value="/listar" />">&larr; Voltar para Listagem</a></li>
		</ul>
	</div>

	<div class="container" >
		<div class="novo-projeto" align="left">
			<div class="form" align="center" style="margin-bottom: 30px">
				<h2>Informações do Projeto</h2>
				<form:form commandName="projeto" id="reg" >
	<br><br>
	<table class="table table-bordered">
					
					<tr class="success">
					<td>
					<div id="ui" style=" float: left; text-align: center;"> <form:label path="nome" > <h4> Nome:</h4> </form:label> </div>
					<div id="uii"  style=" float: left; text-align: center; margin: 10px 0px 0px 10px; " > <label style="font-family: inherit;" ><c:out value="${projeto.nome }"></c:out> </label> </div>
					</td>
					</tr>
					<tr class="success">
					<td>
					<form:label path="status"> <h4> Status: </h4></form:label>
					<label> <c:out value="${projeto.status }"></c:out> </label>
					</td>
					</tr>
					<tr class="success">
					<td>
					<form:label path="nome"> <h4> Criador do Projeto: </h4></form:label>
					<label> <c:out value="${projeto.usuarioCriador.nome }"></c:out> </label>
					</td>
					</tr>
					<tr class="success">
					<td>
					<form:label path="descricao"> <h4> Descrição: </h4></form:label>
					<label> <c:out value="${projeto.descricao }"></c:out> </label>
					</td>
					</tr>
					<tr class="success">
					<td>
					<form:label path="atividades"> <h4> Atividades: </h4></form:label>
					<label> <c:out value="${projeto.atividades }"></c:out> </label>
					</td>
					</tr>
					
					<tr class="success">
					<td>
					<form:label path="participantes"> <h4> Participantes: </h4></form:label>
					<label> <c:out value="${projeto.participantes }"></c:out> </label>
					</td>
					</tr>
					
					<tr class="success">
					<td>
					<form:label path="local"> <h4> Local: </h4> </form:label>
					<label> <c:out value="${projeto.local }"></c:out> </label>
					</td>
					</tr>
					
					<tr class="success">
					<td>
					<form:label path="quantidadeBolsa"> <h4> Número de Bolsas: </h4></form:label>
					<label> <c:out value="${projeto.quantidadeBolsa }"></c:out> </label>
					</td>
					</tr>
					<tr class="success">
					<td>		
					<form:label path="documentos"> <h4> Arquivos: </h4></form:label>
					<c:forEach var="documento" items="${projeto.documentos}">
					<a href="<c:url value="/files/${documento.id}"></c:url>">${documento.nomeOriginal}</a>
					<br>
					</c:forEach>
					</td>
					</tr>
					
					</table>
					
					<table class="table table-bordered">
					<tr class="warning">
					<td>
					<form:label path="inicio">Início:</form:label>
						<form:input type="text" path="inicio" 
								id="datainicio" readonly="true" style="width: 79px;"/>
					</td>
					<td>
					<form:label path="termino">Término:</form:label>
						<form:input type="text" path="termino" 
								id="datatermino" readonly="true" style="width: 79px;"  />
					</td>
			</tr>
					
					</table>
					
					
	
				</form:form>
			
			</div>
		</div>
	</div>

	<ul class="pager">
		<li class="previous"><a href="/exemplo-jpa-spring-mvc/listar" style="margin-bottom: 80px">&larr; Voltar para
				Listagem</a></li>
	</ul>

	<jsp:include page="../modulos/footer.jsp" />

	<script src="<c:url value="/webjars/jquery/2.1.0/jquery.min.js" />"></script>
	<script src="<c:url value="/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.min.js" />"></script>
	<script src="<c:url value="/webjars/jquery-validation/1.12.0/jquery.validate.min.js" />"></script>
</body>
</html>
