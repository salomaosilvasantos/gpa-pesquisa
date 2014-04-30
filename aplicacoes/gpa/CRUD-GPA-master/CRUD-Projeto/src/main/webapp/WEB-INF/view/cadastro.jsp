<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="utf-8">
<link
	href="<c:url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />"
	rel="stylesheet" />
<script src="<c:url value="/webjars/jquery/2.1.0/jquery.min.js" />"></script>
<script
	src="<c:url value="/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.min.js" />"></script>
<script
	src="<c:url value="/webjars/jquery-validation/1.12.0/jquery.validate.min.js" />"></script>
</head>

<body>
	<ul class="pager">
		<li class="previous"><a href="home">&larr; Voltar para Início</a></li>
	</ul>

	<div class="container">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Cadastrar Projeto</h2>
				<form action="<c:url value="/projetos/new" />" method="POST"
					class="form-horizontal" id="add-projeto-form"
					onsubmit="return valida(this);">

						<label>Nome do Projeto:</label> 
						<input type='text' name='nome'
						value='' id="nomeprojeto" class="form-control"  required>
						
						<label>Data	de Inicio:</label>
						<input type="text" name='inicio' class="form-control"
						value="dd/mm/aaaa" id="datainicio"
						onfocus="if (this.value=='dd/mm/aaaa'){this.value='';}"
						onblur="if (this.value==''){this.value='dd/mm/aaaa';}"  />
						
						<label>Data	de Término: </label>
						<input type="text" name='termino'
						class="form-control" value="dd/mm/aaaa" id="datatermino"
						onfocus="if (this.value=='dd/mm/aaaa'){this.value='';}"
						onblur="if (this.value==''){this.value='dd/mm/aaaa';}"  />
						
						<label>Descrição do Projeto:</label>
						<input type='text' name='descricao'
						id="descricaoprojeto" class="form-control" />
						
					<label>Atividades:</label>
					<input type='text' name='atividades' value=''
						id="atividades" class="form-control" />
						
						<label>Número de Bolsas:</label>
						<input type="number" name='numero_bolsas' id="numbolsas"
							class="form-control"  required/>
					
						<label>Local do Projeto:</label>
						<input type='text' name='local' value=''
							id="localprojeto" class="form-control" />
					
					
						<label>Participantes:</label>
						<input type='text' class="form-control"
							name='participantes' id="participantes" />
					
					
						<input name="reset" type="reset" value="Limpar Campos"
							class="btn btn-default" />
						<input name="submit" type="submit"
							class="btn btn-default" value="Cadastrar Projeto" />
				</form>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#datatermino,#datainicio").mask("99/99/9999");

</script>

<style type="text/css">
	.container{
	width:30%;
	}
	
	.btn-default{
		margin-top:5%;
	}
</style>

</html>