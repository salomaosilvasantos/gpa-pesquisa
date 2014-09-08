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
	<title>Atribuir Parecerista</title>
</head>

<body>
	<jsp:include page="../modulos/header.jsp" />

	<div class="container">
		<div class="atribuirParecerista" align="center">
			<div class="form" align="center">
				<h2>Atribuir Parecerista</h2>
				

				<form:form servletRelativeAction="/projeto/diretor/atribuirParecerista" method="POST" cssClass="form-horizontal">

					<input type="hidden" name="projetoId" value="${projetoId}">
					
					<div class="form-group">
						<label for="parecerista" class="col-sm-2 control-label">Parecerista:</label>
						<div class="col-sm-4">
							<select name="parecerista" class="form-control">
								<c:forEach items="${usuarios}" var="usuario">
									<option value="${usuario.id}">${usuario.nome}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="prazo" class="col-sm-2 control-label">Prazo:</label>
						<div class="col-sm-2">
							<input id="prazo" type="text" name="prazo" value="${prazo}" class="form-control data" placeholder="Prazo"/>
							<c:if test="${not empty error_prazo}">
								<div class="error-validation">
									<span>${error_prazo}</span>
								</div>
							</c:if>
						</div>
					</div>
					
					<div class="form-group">
						<label for="comentario" class="col-sm-2 control-label">Observações:</label>
						<div class="col-sm-10">
							<textarea id="comentario" name="comentario" class="form-control" rows="5" placeholder="Comentário" ></textarea>
						</div>
					</div>

					<%-- <div id="envolve2" style="width: 1000px;">
						<div id="div1_1"
							style="width: 200px; margin: 30px 80px 0px 170px; float: left; text-align: center;">
							<h4>Parecerista</h4>
						</div>
						<div id="div_2_2"
							style="width: 200px; margin: 30px 80px 0px 190px; float: left; text-align: center;">
							<h4>Prazo</h4>
						</div>
						<div id="clear"></div>
						<div id="div_1" class="input-group"
							style="width: 100px; margin: 0px 200px 0px 100px; float: left; text-align: center;">
							<select style="width: 350px;" name="parecerista"
								class="form-control">
								<c:forEach items="${usuarios}" var="usuario">
									<option value="${usuario.id}">${usuario.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div id="div_2" class="input-group date"
							style="width: 200px; float: left; text-align: center;">
							<input type="text" id="prazo" name="prazo" required="required" 
								style="width: 159px; height: 34px;" /><span
								class="input-group-addon" style="width: 09px; height: 09px;"><i
								class="glyphicon glyphicon-th"></i></span>
						</div>
					</div>
					
					

					<br>
					<br>
					<br>
					<br>
					<div class="control-group">
						<div>
							<h4>Comentarios adicionais:</h4>
						</div>
						<textarea name="comentario_diretor" class="form-control" id="text"
							rows="3" id="comentario_diretor" style="width: 800px;" required="true" title="Comentario obrigatorio"></textarea>
						<div class="controls"></div>
					</div>

					<br> --%>

					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Atribuir" />
						<a href="<c:url value="/projeto/index"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
			</form:form>
			</div>

		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp"></jsp:include>

</body>

<!-- <script type="text/javascript">
	$(document)
			.ready(
					function() {
						$.fn.datepicker.dates['pt-BR'] = {
						        days: ["Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"],
						        daysShort: ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom"],
						        daysMin: ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab", "Dom"],
						        months: ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
						        monthsShort: ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"],
						        today: "Hoje"
						};
						$('.input-group.date').datepicker({
							format : "dd/mm/yyyy",
							startDate : "01-01-2012",
							endDate : "01-01-2015",
							todayBtn : "linked",
							autoclose : true,
							language: "pt-BR",
							todayHighlight : true
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
						$.validator.addMethod('positiveNumber',
								function(value) {
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
							errorPlacement : function(error, element) {
								// if the input has a prepend or append element, put the validation msg after the parent div
								if (element.parent().hasClass('input-prepend')
										|| element.parent().hasClass(
												'input-append')) {
									error.insertAfter(element.parent());
									// else just place the validation message immediatly after the input
								} else {
									error.insertAfter(element);
								}
							},
							errorElement : "small", // contain the error msg in a small tag
							wrapper : "div", // wrap the error message and small tag in a div
							highlight : function(element) {
								$(element).closest('.control-group').addClass(
										'error'); // add the Bootstrap error class to the control group
							},
							success : function(element) {
								$(element).closest('.control-group')
										.removeClass('error'); // remove the Boostrap error class from the control group
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
														depends : function() {
															return $(
																	'input[name="inicio"]')
																	.val().length > 0
																	&& $(
																			'input[name="termino"]')
																			.val().length > 0;
														}
													}
												},
												termino : {
													dateBR : {
														depends : function() {
															return $(
																	'input[name="termino"]')
																	.val().length > 0
																	&& $(
																			'input[name="inicio"]')
																			.val().length > 0;
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
												$(element).closest(
														'.control-group')
														.addClass('has-error')
														.removeClass(
																'has-success');

											},
											unhighlight : function(element) {
												$(element)
														.closest(
																'.control-group')
														.removeClass(
																'has-error')
														.addClass('has-success');

											}
										});
					});
</script> -->

</html>