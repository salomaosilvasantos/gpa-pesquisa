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
</head>

<body>
	<ul class="pager">
		<li class="previous"><a href="index">&larr; Voltar para
				Inicio</a></li>
	</ul>

	<div class="container">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Cadastrar Projeto</h2>
				<form:form commandName="projeto" action="novoProjeto" method="POST"
					cssClass="form-horizontal ">

					<div class="control-group">
						<label for="nome" class=" control-label">Nome do Projeto:</label>
						<div class="controls">
							<input type='text' name='nome' value='' id="nome"
								class="form-control" />
							<form:errors path="nome" />
						</div>
					</div>

					<div class="control-group">
						<label for="datainicio" class="control-label">Data de Inicio:</label>
						<div class="controls">
							<input type="text" name='inicio' class="form-control"
								value="dd/mm/aaaa" id="datainicio" />
						</div>
					</div>

					<div class="control-group">
						<label for="datatermino" class="control-label">Data de Término:</label>
						<div class="controls">
							<input type="text" name='termino' class="form-control"
								value="dd/mm/aaaa" id="datatermino" />
						</div>
					</div>

					<div class="control-group">
						<label for="descricaoprojeto" class="control-label">Descrição do
							Projeto:</label>
						<div class="controls">
							<input type="text" name='descricao' class="form-control"
								id="descricaoprojeto" />
							<form:errors path="descricao" />
						</div>
					</div>

					<div class="control-group">
						<label for="atividades" class="control-label">Atividades:</label>
						<div class="controls">
							<input type="text" name='atividades' class="form-control"
								id="atividades" />
						</div>
					</div>

					<div class="control-group">
						<label for="numbolsas" class="control-label">Número de
							Bolsas:</label>
						<div class="controls">
							<input type="number" name='numero_bolsas' class="form-control"
								id="numbolsas" />
							<form:errors path="numero_bolsas" />
						</div>
					</div>

					<div class="control-group">
						<label for="localprojeto" class="control-label">Local do
							Projeto:</label>
						<div class="controls">
							<input type="text" name='local' class="form-control"
								id="localprojeto" />
						</div>
					</div>

					<div class="control-group">
						<label for="participantes" class="control-label">Participantes:</label>
						<div class="controls">
							<input type="text" name='participantes' class="form-control"
								id="participantes" />
						</div>
					</div>
					<div class="controls">
					<br>
						<input name="reset" type="reset" value="Limpar Campos"	class="btn btn-primary" />
						<input name="submit" type="submit"	class="btn btn-success" value="Cadastrar Projeto" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<ul class="pager">
		<li class="previous"><a href="listar">&larr; Voltar para
				Listagem</a></li>
	</ul>


	<script src="<c:url value="/webjars/jquery/2.1.0/jquery.min.js" />"></script>
	<script
		src="<c:url value="/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.min.js" />"></script>
	<script
		src="<c:url value="/webjars/jquery-validation/1.12.0/jquery.validate.min.js" />"></script>
</body>


<script type="text/javascript">
	$(document).ready(function() {
		$("#datatermino,#datainicio").mask("99/99/9999");
	});
</script>

<style type="text/css">
.container {
	width: 30%;
}

.btn-default {
	margin-top: 5%;
}
</style>

</html>