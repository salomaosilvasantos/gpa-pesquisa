<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
		<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"></script>
		<meta charset="utf-8">
		<link href="<c:url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/estilo.css" />" rel="stylesheet"/>
			<title>Cadastro de Projetos</title>
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
					cssClass="form-horizontal registrationForm">

					<div class="control-group">
						<form:label path="nome" cssClass="control-label">Nome do Projeto:</form:label>
						<div class="controls">
							<form:input path="nome" cssClass="form-control" />
							<form:errors path="nome" />
						</div>
					</div>

						<div class="control-group">
							<form:label path="inicio" cssClass="control-label">Data de
								Inicio:</form:label>
							<div class="controls">
								<form:input path="inicio" cssClass="form-control" id="datainicio" />
								<form:errors path="inicio"  />
							</div>
						</div>
	
						<div class="control-group">
							<form:label path="termino" cssClass="control-label">Data de
								Término:</form:label>
							<div class="controls">
								<form:input path="termino" cssClass="form-control"
									id="datatermino" />
								<form:errors path="termino"  />
							</div>
						</div>

					<div class="control-group">
						<form:label path="descricao" cssClass="control-label">Descrição
							do Projeto:</form:label>
						<div class="controls">
							<form:input path="descricao" cssClass="form-control"
								id="descricaoprojeto" />
							<form:errors path="descricao"  />
						</div>
					</div>

					<div class="control-group">
						<form:label path="atividades" cssClass="control-label">Atividades:</form:label>
						<div class="controls">
							<form:input path="atividades" cssClass="form-control"
								id="atividades" />
						</div>
					</div>

					<div class="control-group">
						<form:label path="numero_bolsas" cssClass="control-label">Número de
							Bolsas:</form:label>
						<div class="controls">
							<form:input type="number" placeholder="1" path="numero_bolsas"
								cssClass="form-control error" />
							<form:errors path="numero_bolsas" />
						</div>
					</div>

					<div class="control-group">
						<form:label path="local" cssClass="control-label">Local do
							Projeto:</form:label>
						<div class="controls">
							<form:input path="local" cssClass="form-control"
								id="localprojeto" />
						</div>
					</div>
					
					<div class="control-group">
						<form:label path="participantes" cssClass="control-label">Participantes:</form:label>
						<div class="controls">
							<form:input path="participantes" cssClass="form-control"
								id="participantes" />
						</div>
					</div>					

					<div class="controls">
						<input name="reset" type="reset" value="Limpar Campos"
							class="btn btn-default acoes" /> <input name="submit" type="submit"
							class="btn btn-success acoes" value="Cadastrar Projeto" />
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
	<script	src="<c:url value="/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.min.js" />"></script>
	<script	src="<c:url value="/webjars/jquery-validation/1.12.0/jquery.validate.min.js" />"></script>

</body>


<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//Método que valida o dia mês e ano dd/MM/yyyy
						jQuery.validator
								.addMethod(
										"dateBR",
										function(value, element) {											
											if (value.length != 10)
												return (this.optional(element) || false);
											var data = value;
											var dia = data.substr(0, 2);
											var barra1 = data.substr(2, 1);
											var mes = data.substr(3, 2);
											var barra2 = data.substr(5, 1);
											var ano = data.substr(6, 4);
											if (data.length != 10
													|| barra1 != "/"
													|| barra2 != "/"
													|| isNaN(dia) || isNaN(mes)
													|| isNaN(ano) || dia > 31
													|| mes > 12)
												return (this.optional(element) || false);
											if ((mes == 4 || mes == 6
													|| mes == 9 || mes == 11)
													&& dia == 31)
												return (this.optional(element) || false);
											if (mes == 2
													&& (dia > 29 || (dia == 29 && ano % 4 != 0)))
												return (this.optional(element) || false);
											if (ano < 1900)
												return (this.optional(element) || false);
											return (this.optional(element) || true);
										}, "Informe uma data válida");
											
						//Validando numero de bolsas positivo
						$.validator.addMethod('positiveNumber',    function (value) { 
							        return Number(value) >= 0;
							    }, 'Entre com um numero positivo');

						$("#datatermino,#datainicio").mask("99/99/9999");

						//código para input só ser números
						var specialKeys = new Array();
						specialKeys.push(8);
						$("#numero_bolsas")
								.bind(
										"keypress",
										function(e) {
											var keyCode = e.which ? e.which
													: e.keyCode;
											var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys
													.indexOf(keyCode) != -1);
											return ret;
										});
						$("#numero_bolsas").bind("paste", function(e) {
							return false;
						});
						$("#numero_bolsas").bind("drop", function(e) {
							return false;
						});

						
						 jQuery.validator.setDefaults({
							 errorPlacement: function(error, element) {
							 // if the input has a prepend or append element, put the validation msg after the parent div
							 if(element.parent().hasClass('input-prepend') || element.parent().hasClass('input-append')) {
							 error.insertAfter(element.parent());	
							 // else just place the validation message immediatly after the input
							 } else {
							 error.insertAfter(element);
							 }
							 },
							 errorElement: "small", // contain the error msg in a small tag
							 wrapper: "div", // wrap the error message and small tag in a div
							 highlight: function(element) {
							 $(element).closest('.control-group').addClass('error'); // add the Bootstrap error class to the control group
							 },
							 success: function(element) {
							 $(element).closest('.control-group').removeClass('error'); // remove the Boostrap error class from the control group
							 }
							 });
						
						
						
						
						
						$(".registrationForm")
								.validate(
										{											
											rules : {
												nome : {
													required : true,
													minlength : 2
												},
												descricao : {
													required : true,
													minlength : 5
												},
												inicio : {
													dateBR : {
														depends : function () {
															 return $('input[name="inicio"]').val().length > 0 && $('input[name="termino"]').val().length > 0;
														}
													}													
												},
												termino : {
													dateBR : {
														depends : function () {
															return $('input[name="termino"]').val().length > 0 && $('input[name="inicio"]').val().length > 0;
														}
													}														
												},
												numero_bolsas : {
													positiveNumber : true
												}
											},
											messages : {
												nome : {
													required : "Campo obrigatório!",
													minlength : "Campo deve ter no mínimo 2 caracteres!"
												},
												descricao : {
													required : "Campo obrigatório!",
													minlength : "Campo deve ter no mínimo 5 caracteres!"
												},
												inicio : {
													dateBR : "Data Inválida"
												},
												termino : {
													dateBR : "Data Inválida"
												},
												positiveNumber : "Somente números positivos"
											},

											highlight : function(element) {
												$(element).closest('.control-group').addClass('has-error').removeClass('has-success');
												
											},																	
											unhighlight : function(element) {
												$(element).closest('.control-group').removeClass('has-error').addClass('has-success');
												
											}
										});
					});
</script>

</html>