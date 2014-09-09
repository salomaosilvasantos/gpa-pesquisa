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
	<title>Cadastro de Projetos</title>
</head>
<body>

	<jsp:include page="../modulos/header.jsp" />
	
	 <div class="container">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Novo Projeto</h2>
				<form:form id="adicionarProjetoForm" role="form" commandName="projeto" servletRelativeAction="/projeto/cadastrar" method="POST" cssClass="form-horizontal">

					<div class="form-group">
						<label for="nome" class="col-sm-2 control-label">Nome:</label>
						<div class="col-sm-10">
							<form:input id="nome" path="nome" cssClass="form-control" placeholder="Nome do projeto"/>
							<div class="error-validation">
								<form:errors path="nome"></form:errors>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="descricao" class="col-sm-2 control-label">Descrição:</label>
						<div class="col-sm-10">
							<form:textarea id="descricao" path="descricao" class="form-control" rows="5" placeholder="Descrição" ></form:textarea>
							<div class="error-validation">
								<form:errors path="descricao"></form:errors>
							</div>
						</div>
						
					</div>

					<div class="form-group">
						<label for="inicio" class="col-sm-2 control-label">Início:</label>
						<div class="col-sm-2">
							<form:input id="inicio" type="text" path="inicio" cssClass="form-control data" placeholder="Data de Início"/>
							<div class="error-validation">
								<form:errors path="inicio"></form:errors>
							</div>
							<c:if test="${not empty error_inicio}">
								<div class="error-validation">
									<span>${error_inicio}</span>
								</div>
							</c:if>
						</div>
						
						<label for="termino" class="col-sm-2 control-label">Término:</label>
						<div class="col-sm-2">
							<form:input id="termino" type="text" path="termino" cssClass="form-control data" placeholder="Data de Término"/>
							<div class="error-validation">
								<form:errors path="termino"></form:errors>
							</div>
							<c:if test="${not empty error_termino}">
								<div class="error-validation">
									<span>${error_termino}</span>
								</div>
							</c:if>							
						</div>
						
						<label for="bolsas" class="col-sm-2 control-label">Número de bolsas:</label>
						<div class="col-sm-2">
							<form:input id="bolsas" type="number" min="0" placeholder="0" path="quantidadeBolsa" cssClass="form-control"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="local" class="col-sm-2 control-label">Local:</label>
						<div class="col-sm-10">
							<form:input id="local" path="local" cssClass="form-control" placeholder="Local do projeto"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="participantes" class="col-sm-2 control-label">Participantes:</label>
						<div class="col-sm-10">
							<form:input id="participantes" path="participantes" cssClass="form-control" placeholder="Participantes do projeto"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="atividades" class="col-sm-2 control-label">Atividades:</label>
						<div class="col-sm-10">
							<form:textarea id="atividades" path="atividades" name="atividades" class="form-control" rows="5" placeholder="Atividades" ></form:textarea>
						</div>
					</div>
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Cadastrar" />
						<a href="<c:url value="/projeto/index"></c:url>" class="btn btn-default">Cancelar</a>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp" />

</body>


<script type="text/javascript">
	$(document)
			.ready(
					function($) {
						/* $('form').validate({
					        rules: {
					            nome: {
					                minlength: 2,
					                required: true
					            },
					            descricao: {
					                minlength: 5,
					                required: true
					            }
					        },
					        messages : {
								nome : {
									required : "Campo obrigatório",
									minlength : "O campo deve ter no mínimo 2 caracteres"
								},
								descricao : {
									required : "Campo obrigatório",
									minlength : "O campo deve ter no mínimo 5 caracteres"
								}
							},
					        highlight: function(element) {
					            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
					        },
					        unhighlight: function(element) {
					            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');;
					        },
					        errorElement: 'span',
					        errorClass: 'help-block',
					        errorPlacement: function(error, element) {
					            if(element.parent('.input-group').length) {
					                error.insertAfter(element.parent());
					            } else {
					                error.insertAfter(element);
					            }
					        }
					    }); */
						/* $('div.error-validation:has(span)').find('span').css('color', '#a94442');
						$('div.error-validation:has(span)').find('span').parent().parent().parent().addClass('has-error has-feedback'); */
						/* $.fn.datepicker.dates['pt-BR'] = {
							days : [ "Domingo", "Segunda", "Terça", "Quarta",
									"Quinta", "Sexta", "Sábado", "Domingo" ],
							daysShort : [ "Dom", "Seg", "Ter", "Qua", "Qui",
									"Sex", "Sáb", "Dom" ],
							daysMin : [ "Dom", "Seg", "Ter", "Qua", "Qui",
									"Sex", "Sab", "Dom" ],
							months : [ "Janeiro", "Fevereiro", "Março",
									"Abril", "Maio", "Junho", "Julho",
									"Agosto", "Setembro", "Outubro",
									"Novembro", "Dezembro" ],
							monthsShort : [ "Jan", "Fev", "Mar", "Abr", "Mai",
									"Jun", "Jul", "Ago", "Set", "Out", "Nov",
									"Dez" ],
							today : "Hoje"
						}; */

						/* $("input.data").datepicker({
							format : "dd/mm/yyyy",
							todayBtn : "linked",
							autoclose : true,
							language : "pt-BR",
							todayHighlight : true
						}); */
						//Método que valida o dia mês e ano dd/MM/yyyy
						/* jQuery.validator
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
										}, "Informe uma data válida"); */

						//Validando numero de bolsas positivo
						/* $.validator.addMethod('positiveNumber',
								function(value) {
									return Number(value) >= 0;
								}, 'Entre com um numero positivo');

						//código para input só ser números
						var specialKeys = new Array();
						specialKeys.push(8);
						$("#quantidadeBolsa")
								.bind(
										"keypress",
										function(e) {
											var keyCode = e.which ? e.which
													: e.keyCode;
											var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys
													.indexOf(keyCode) != -1);
											return ret;
										});

						$("#quantidadeBolsa").bind("paste", function(e) {
							return false;
						});

						$("#quantidadeBolsa").bind("drop", function(e) {
							return false;
						}); */

						/* jQuery.validator.setDefaults({
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
							wrapper : "span", // wrap the error message and small tag in a div
							highlight : function(element) {
								$(element).closest('.control-group').addClass(
										'error'); // add the Bootstrap error class to the control group
							},

							success : function(element) {
								$(element).closest('.control-group')
										.removeClass('error'); // remove the Boostrap error class from the control group
							}
						}); */

						/* $(".registrationForm")
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
																	'#datainicio')
																	.val().length > 0
																	&& $(
																			'#datatermino')
																			.val().length > 0;
														}
													}
												},
												termino : {
													dateBR : {
														depends : function() {
															return $(
																	'#datatermino')
																	.val().length > 0
																	&& $(
																			'#datainicio')
																			.val().length > 0;
														}
													}
												},
												quantidadeBolsa : {
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
										});*/
					}(jQuery));
</script>

</html>