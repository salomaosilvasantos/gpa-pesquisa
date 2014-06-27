<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<link href="http://eternicode.github.io/bootstrap-datepicker/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet">
		<link href="<c:url value="/resources/css/estilo.css" />" rel="stylesheet"/>
		
		<script type="text/javascript">
			$( document ).ready(function() {
				$('div:has(span.error)').find('span.error').css('color', '#a94442');
				$('div:has(span.error)').find('span.error').parent().parent().addClass('has-error has-feedback');
			});
		</script>
		
			<title>Atribuir Parecerista</title>
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

#clear {
 clear:both; 
}

</style>
<body>
	<jsp:include page="../modulos/header.jsp" />

	<ul class="pager">
		<li class="previous"><a href="/exemplo-jpa-spring-mvc/index">&larr; Voltar para Inicio</a></li>
	</ul>

	<div class="container">
		<div class="atribuirParecerista" align="center">
			<div class="form" align="center">
				<h2>Atribuir Parecerista</h2>
				
				<br> <br>
				
				<c:if test="${error != null }">
					<div class="alert alert-danger" role="alert" style=" text-size: 30px;">${error}</div>
				</c:if>
				
				<c:if test="${error == null }">
					<form:form action="/exemplo-jpa-spring-mvc/atribuirParecerista" method="GET"
						cssClass="form-horizontal registrationForm">
	<br> 
					<input type="hidden" name="projetoId" value="${projetoId}">
					
					<div id="envolve" style=" width: 1000px; height: 80px; margin: 30px 80px 0px 70px;" align="center">
						
						<div id="clear">
						</div>
										
						<div id="div_1"  class="input-group" style=" width: 100px; margin: 0px 200px 0px 100px; float: left; text-align: center;">
							<label  cssClass="control-label">Parecerista</label>
							<select style=" width: 350px;" name="parecerista" class="form-control">
								<c:forEach items="${usuarios}" var="usuario">
									<option value="${usuario.id}">${usuario.nome}</option>
								</c:forEach>
							</select>
						</div>
									
						<div id="div_2"  class="input-group date" style=" width: 200px; float: left; text-align: center; " >
							<label cssClass="control-label">Prazo</label>
								<input type="date" cssClass="form-control" id="prazo" name="prazo" /><span class="input-group-addon" style="width: 50px; height: 50px;"><i class="glyphicon glyphicon-th"></i></span>
								<form:errors path="prazo" cssClass="error" />
						</div>
					</div>
					
				</div>
	
				<br> <br>
	
						<div class="control-group">
							<div ><h4>Comentarios adicionais:</h4></div>
								<textarea name="comentario_diretor" class="form-control" id="text" rows="3" id="comentario_diretor" style="width: 800px;"></textarea>
							<div class="controls">
							</div>
						</div>
	
				<br> 
	
						<div class="controls">
							<input name="submit" type="submit" class="btn btn-primary" value="Confirmar" />
							<a href="/exemplo-jpa-spring-mvc/listar" class="btn btn-default" >Cancelar</a>
						</div>
					</form:form>
				</c:if>
			</div>
		</div>
	</div>
	<ul class="pager">
		<li class="previous"><a href="/exemplo-jpa-spring-mvc/listar">&larr; Voltar para Listagem</a></li>
	</ul>
	
	<script src="<c:url value="/webjars/jquery/2.1.0/jquery.min.js" />"></script>
	<script	src="<c:url value="/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.min.js" />"></script>
	<script	src="<c:url value="/webjars/jquery-validation/1.12.0/jquery.validate.min.js" />"></script>
	<script src="http://eternicode.github.io/bootstrap-datepicker/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	
	<jsp:include page="../modulos/footer.jsp"></jsp:include>
	
</body>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('.input-group.date').datepicker({
					        format: "dd/mm/yyyy",
					        startDate: "01-01-2012",
					        endDate: "01-01-2015",
					        todayBtn: "linked",
					        autoclose: true,
					        todayHighlight: true
					    });
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

						$("#prazo").mask("99/99/9999");

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
												prazo : {
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