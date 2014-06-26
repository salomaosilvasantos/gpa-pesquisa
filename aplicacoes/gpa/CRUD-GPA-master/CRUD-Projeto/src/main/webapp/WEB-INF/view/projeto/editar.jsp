<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Projeto</title>
</head>
<body>
	
<head>

<link href="<c:url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />" rel="stylesheet" />
<script src="<c:url value="/webjars/jquery/2.1.0/jquery.min.js" />"></script>
<script src="<c:url value="/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.min.js" />"></script>
<script src="<c:url value="/webjars/jquery-validation/1.12.0/jquery.validate.min.js" />"></script>
</head>
<style>
#envolve {
 width: 890px;
}

#div_3 {
 float: left;
 text-align: center;
 
 
}
#center {
 background-color: #d0e4fe;
 
 
}
#text {
 
 
}
#clear {
 clear:both; 
}

</style>
<body>
	<ul class="pager">
		<li class="previous"><a href="<c:url value="/listar" />">&larr; Voltar para Listagem</a></li>
	</ul>
	<div class="container" >
	<div class="novo-projeto" align="left">
		<div class="form" align="center">
			<h2>Editar Projeto</h2>
			<form:form commandName="projeto" id="reg"
				action="editarProjetoForm">

				<form:label path="id">ID:</form:label>
				<br>
				<form:input path="id" readonly="true" class="form-control"/>
				<br>

				<div class="control-group">
						<form:label path="nome" cssClass="control-label" > <h4> Nome do Projeto: </h4></form:label>
						<div class="controls">
							<form:input path="nome" cssClass="form-control" id="text" />
							<form:errors path="nome" />
						</div>
					</div>

				<form:label path="status">Status:</form:label>
				<br>
				<form:input path="status" readonly="true" class="form-control"/>
				<br>
				
				<div class="control-group">
							<div ><h4>Descrição do Projeto:</h4></div>
							<textarea name="descricao" class="form-control" id="text" rows="3" id="descricaoprojeto"></textarea>
						<div class="controls">
						
						</div>
					</div>
				
					<div id="envolve" style=" width: 1000px;">
 
								<div id="envolve2" style=" width: 1000px;">
								<div id="div1_1" style=" width: 200px; margin: 30px 80px 0px 70px ; float: left; text-align: center;"><h4>Data Início</h4></div>
								<div id="div_2_2" style=" width: 200px; margin:30px 80px 0px 50px; float: left; text-align: center; "><h4>Data Término</h4></div>
								<div id="div_3_3" style="width: 250px; float: left; text-align: center; margin:35px 0px 0px 30px ; " ><h4>Numero de Bolsas</h4></div>
								</div>
								<div id="clear">
								</div>
								<div id="div_1"  class="input-group date" style=" width: 200px; margin: 30px 80px 0px 70px ; float: left; text-align: center;">
								<form:label path="inicio" cssClass="control-label"></form:label>
								<form:input  type="text" path="inicio" cssClass="form-control" id="datainicio" /><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
								<form:errors path="inicio"  />
								
								</div>
							
						
								<div id="div_2"  class="input-group date" style=" width: 200px; margin:30px 80px 0px 50px; float: left; text-align: center; " >
								<form:label path="termino" cssClass="control-label"></form:label>
								<form:input type="text" path="termino" cssClass="form-control" id="datatermino" /><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
								<form:errors path="termino"  />
							</div>
						

						<div id="div_3" style=" position: relative; width: 250px; float: left; text-align: center; margin:35px 0px 0px 30px; " >
							 <form:input type="number" placeholder="                    0" path="numero_bolsas" />
						</div>
						

</div>

<div id="clear">
</div>
		<br>
<br>		
					<div class="control-group">
						<form:label path="local" cssClass="control-label" > <h4> Local do Projeto: </h4></form:label>
						<div class="controls">
							<form:input path="local" cssClass="form-control"
								id="text" />
						</div>
					</div>
					
					<div class="control-group">
						<form:label path="participantes" cssClass="control-label" > <h4> Participantes: </h4></form:label>
						<div class="controls">
							<form:input path="participantes" cssClass="form-control" id="text"  />
						</div>
					</div>					
					<div class="control-group">
					<div><h4>Atividades</h4></div>
						<textarea name="atividades" class="form-control" rows="3" id="text" ></textarea>
						
					</div>
					
	
												
				<div><h4>Arquivos</h4></div>
				<br>
				<form:input type="file" class="form-control" path="documentos" />

				<br>
				<input type="submit" value="Salvar" class="btn btn-default"/>
				<a href="<c:url value="/listar" />"><input type="button" value="Cancelar" class="btn btn-default"/></a>

			</form:form>
		</div>
	</div>
</div>
</body>
</html>
