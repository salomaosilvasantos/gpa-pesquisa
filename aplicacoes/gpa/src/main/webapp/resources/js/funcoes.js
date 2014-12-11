$(document).ready(function() {
	$('#adicionarProjetoForm').bootstrapValidator({
		group: '.form-item',
        feedbackIcons: {
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nome: {
                validators: {
                    stringLength: {
                        min: 2,
                        message: 'O nome deve ter no mínimo 2 caracteres'
                    }
                }
            },
            descricao: {
                validators: {
                    stringLength: {
                        min: 5,
                        message: 'A descrição deve ter no mínimo 5 caracteres'
                    }
                }
            },
            quantidadeBolsa: {
            	validators: {
            		integer: {
                        message: 'Digite um número válido'
                    }
            	}
            },
            cargaHoraria: {
            	validators: {
            		integer: {
                        message: 'Digite um número válido'
                    }
            	}
            },
            inicio :{
            	validators: {
            		callback: {
                        message: 'A data de início deve ser anterior à data de término',
                        callback: function(value, validator) {
                        	var termino = validator.getFieldElements('termino').val();
                        	if(value != "" && termino != "") {
                        		termino = moment(termino, "DD/MM/YYYY").format("DD/MM/YYYY");
	                        	var inicio = moment(value, "DD/MM/YYYY").format("DD/MM/YYYY");
	                        	if(moment(termino, "DD/MM/YYYY").isBefore(moment(inicio, "DD/MM/YYYY"))) {
	                        		return false;
	                        	}
                        	}
                        	return true;
                        }
                    }
            	}
            }
            
        }
    });
	
	$('#submeterProjetoForm').bootstrapValidator({
		group: '.form-item',
		excluded: ':disabled',
        feedbackIcons: {
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nome: {
                validators: {
                    stringLength: {
                        min: 5,
                        message: 'O nome deve ter no mínimo 5 caracteres'
                    }
                }
            },
            quantidadeBolsa: {
            	validators: {
            		integer: {
                        message: 'Digite um número válido'
                    }
            	}
            },
            cargaHoraria: {
            	validators: {
            		integer: {
                        message: 'Digite um número válido'
                    }
            	}
            },
            inicio :{
            	validators: {
            		callback: {
                        message: 'A data de início deve ser anterior à data de término',
                        callback: function(value, validator) {
                        	var termino = validator.getFieldElements('termino').val();
                        	if(value != "" && termino != "") {
                        		termino = moment(termino, "DD/MM/YYYY").format("DD/MM/YYYY");
	                        	var inicio = moment(value, "DD/MM/YYYY").format("DD/MM/YYYY");
	                        	if(moment(termino, "DD/MM/YYYY").isBefore(moment(inicio, "DD/MM/YYYY"))) {
	                        		return false;
	                        	}
                        	}
                        	return true;
                        }
                    }
            	}
            }
            
        }
    });
	
	$('#atribuirPareceristaForm, #emitirParecerForm, #avaliarProjetoForm').bootstrapValidator({
		group: '.form-item',
		excluded: ':disabled',
        feedbackIcons: {
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        }
    });
	
	// Usado para não apagar a máscara e enviar somente o valor para o servidor
	$("#adicionarProjetoForm, #submeterProjetoForm").submit(function() {
		$('#valorDaBolsa').val($('#bolsa').maskMoney('unmasked')[0]);
	});
	
	// Máscaras
    $('[name="bolsa"]').maskMoney({prefix:'R$ ', showSymbol:true, thousands:'.', decimal:','});
    if($('[name="bolsa"]').val() != '') {
    	$('[name="bolsa"]').maskMoney('mask');
    }
	
    $("#inicio").datepicker({
		format : "dd/mm/yyyy",
		todayBtn : "linked",
		language : "pt-BR",
		todayHighlight : true,
	}).on('changeDate', function(e) {
		$(this).datepicker('hide');
        $('#adicionarProjetoForm, #submeterProjetoForm').bootstrapValidator('revalidateField', 'inicio');
    });
    
    $("#termino").datepicker({
		format : "dd/mm/yyyy",
		todayBtn : "linked",
		language : "pt-BR",
		todayHighlight : true,
		startDate: new Date()
	}).on('changeDate', function(e) {
		$(this).datepicker('hide');
		$('#adicionarProjetoForm, #submeterProjetoForm').bootstrapValidator('revalidateField', 'inicio');
		$('#adicionarProjetoForm, #submeterProjetoForm').bootstrapValidator('revalidateField', 'termino');
    });
    
    $("#prazo").datepicker({
		format : "dd/mm/yyyy",
		todayBtn : "linked",
		language : "pt-BR",
		todayHighlight : true,
		startDate: new Date()
	}).on('changeDate', function(e) {
		$(this).datepicker('hide');
		$('#atribuirPareceristaForm').bootstrapValidator('revalidateField', 'prazo');
    });
    
    $("#participantes, #parecerista, #posicionamento, #avaliacao").select2({
   	 placeholder: "Buscar...",
   	 dropdownCssClass: "bigdrop"
    });
    
    $('div.error-validation:has(span)').find('span').css('color', '#a94442');
    
	$('div.error-validation:has(span)').find('span').parent().parent().parent().addClass('has-error has-feedback');
	
	$('#confirm-delete').on('show.bs.modal', function(e) {
		$(this).find('.modal-body').text('Tem certeza de que deseja excluir o projeto \"' + $(e.relatedTarget).data('name') + '\"?');
		$(this).find('.btn-danger').attr('href', $(e.relatedTarget).data('href'));
	});

	$('#confirm-submit').on('show.bs.modal', function(e) {
		$(this).find('.modal-body').text('Tem certeza de que deseja submeter o projeto \"' + $(e.relatedTarget).data('name') + '\"?');
		$(this).find('.btn-primary').attr('href', $(e.relatedTarget).data('href'));
	});
	
	
	$("#formularioCadastroComentario").validate({
		submitHandler : function(form) {
			var idProjeto = $('#projeto').val();
			var idPessoa = $('#pessoa').val();
			var nomePessoa = $('#pessoa_nome').val();
			var cabecalho = "Comentários do Projeto";
			var textoComentario = $('#textocomentarioInput').val();
			var trimTextoComentario = textoComentario.trim();
			var data = new Date();
			var dataFormatada = ("0" + data.getDate()).substr(-2) + "-" + ("0" + (data.getMonth() + 1)).substr(-2) + "-" + data.getFullYear() 
			+ " " + ('0' + data.getHours()).slice(-2) + ":" + ('0' + data.getMinutes()).slice(-2);
			$.ajax({
				type : "POST",
				data : {
					idProjeto : idProjeto,
					idPessoa : idPessoa,
					texto : textoComentario
				},
				url : "/gpa-pesquisa/comentario/comentarProjeto",
				dataType : "html",
				success : function() {
					$('#comentarioList')
					.prepend(
							'<li id="novoComentario" class="well">'
							+ '<div class="nome_pessoa">'
							+ nomePessoa
							+ '</div>'
							+ '<div class="corpo_texto">'
							+ textoComentario
							+ '</div>'
							+ '<div class="formatacao_data">'
							+ dataFormatada
							+ '</div>'
							+ '</li>');
					$(
					"#headComentarios")
					.show();
					$('html, body')
					.animate(
							{
								scrollTop : $(
								"#novoComentario")
								.offset().top
							},
							1000);
				}
			});

			$("#formularioCadastroComentario")[0].reset();
			return false;
		},
		rules : {
			texto : {
				required : true,
			}
		},
		messages : {
			texto : {
				required : "Campo Obrigatório",
			}
		},
		highlight : function(element) {
			$(element).closest('.form-group')
			.addClass('has-error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-group')
			.removeClass('has-error');
		},
		errorElement : 'span',
		errorClass : 'help-block',
		errorPlacement : function(error,
				element) {
			if (element.parent('.input-group').length) {
				error.insertAfter(element
						.parent());
			} else {
				error.insertAfter(element);
			}
		}
	});

	$('.tab a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
	});

	$('.delete-document').on('click', function (e) {
		var line = this;
		var id = $(this).attr('id');
		e.preventDefault();
		bootbox.dialog({
			message: "Tem certeza de que deseja excluir esse arquivo?",
			title: "Excluir",
			buttons: {
				danger: {
					label: "Excluir",
					className: "btn-danger",
					callback: function() {
						$.ajax({
							type: "POST",
							url: "/gpa-pesquisa/documento/ajax/remover/"+id
						})
						.success(function( result ) {
							if(result.result == 'ok') {
								$(line).parent().parent().remove();
							} else {
								bootbox.alert(result.mensagem, function() {
								});
							}
						});
					}
				},
				main: {
					label: "Cancelar",
					className: "btn-default",
					callback: function() {
					}
				}
			}
		});


		$('input[type=file]').bootstrapFileInput();

		$('.delete-file').click(function(){
			alert($(this).attr('id'));
		});

	});

	$('div.error-validation:has(span)').find('span').css('color', '#a94442');
	$('div.error-validation:has(span)').find('span').parent().parent().parent().addClass('has-error has-feedback');

	$("input.data").datepicker({
		format : "dd/mm/yyyy",
		todayBtn : "linked",
		autoclose : true,
		language : "pt-BR",
		todayHighlight : true
	});

	$('.tab a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});

	$('#confirm-delete').on(
			'show.bs.modal',
			function(e) {
				$(this).find('.btn-danger').attr('href',
						$(e.relatedTarget).data('href'));
			});

	$('#confirm-submit').on(
			'show.bs.modal',
			function(e) {
				$(this).find('.btn-primary').attr('href',
						$(e.relatedTarget).data('href'));
			});

	$('.delete-document').on('click', function(e) {
		var line = this;
		var id = $(this).attr('id');
		e.preventDefault();
		bootbox
		.dialog({
			message : "Tem certeza de que deseja excluir esse arquivo?",
			title : "Excluir",
			buttons : {
				danger : {
					label : "Excluir",
					className : "btn-danger",
					callback : function() {
						$
						.ajax(
								{
									type : "POST",
									url : "/gpa-pesquisa/documento/ajax/remover/"
										+ id
								})
								.success(
										function(
												result) {
											if (result.result == 'ok') {
												$(
														line)
														.parent()
														.parent()
														.remove();
											} else {
												bootbox
												.alert(
														result.mensagem,
														function() {
														});
											}
										});
					}
				},
				main : {
					label : "Cancelar",
					className : "btn-default",
					callback : function() {
					}
				}
			}
		});
	});

	$('input[type=file]').bootstrapFileInput();

	$('.delete-file').click(function() {
		alert($(this).attr('id'));
	});

});
function esconderComentarioSeVazio() {
	var verificaSeListaEstaVazia = $("#comentarioList li").length;
	if (verificaSeListaEstaVazia == 0) {
		$("#headComentarios").hide();
	}
}
