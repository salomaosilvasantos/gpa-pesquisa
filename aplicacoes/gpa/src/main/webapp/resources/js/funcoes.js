$(document).ready(function() {
	
	$('div.error-validation:has(span)').find('span').css('color', '#a94442');
	$('div.error-validation:has(span)').find('span').parent().parent().parent().addClass('has-error has-feedback');
	
	$("input.data").datepicker({
		format : "dd/mm/yyyy",
		todayBtn : "linked",
		autoclose : true,
		language : "pt-BR",
		todayHighlight : true
	});
	
	$('.tab a').click(function (e) {
	  e.preventDefault();
	  $(this).tab('show');
	});
	
	$('#confirm-delete').on('show.bs.modal', function(e) {
	    $(this).find('.btn-danger').attr('href', $(e.relatedTarget).data('href'));
	});
	
	$('#confirm-submit').on('show.bs.modal', function(e) {
	    $(this).find('.btn-primary').attr('href', $(e.relatedTarget).data('href'));
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
    });
	
	$('input[type=file]').bootstrapFileInput();
	
	$('.delete-file').click(function(){
		alert($(this).attr('id'));
	});
	
	
	$('#addParticipante').click(function adicionarParticipante(){
		
				var nomeParticipante = $("#participanteEscolhido").val();
				
				if(!nomeParticipante || 0 === nomeParticipante.length){
					
					 $("#participanteEscolhido").css({'border' : '#a94442 solid 1px','box-shadow' : '1px 1px rgba(0,0,0,.075)'}); 
					 $("#labelParticipante").css("color","#a94442");
				}else{
					
					var participantesSelecionados = $('input[name=participanteSelecionado]:checked');
					var selecionado = false;	
					
					for (i = 0; i < participantesSelecionados.length; i++) { 

							if(participantesSelecionados[i].value === nomeParticipante){
								alert("Você selecionou o usuario '" +nomeParticipante +"' mais de uma vez.");
								selecionado = true;
							}
							
						}
					if(selecionado == false){
						 $("#listaParticipantesCadastrados").append(' <label class="participanteSelecionado" for="participanteSelecionado" style="margin-left:20px">'+nomeParticipante+'</label>' 
							    	+'<input type="checkbox" class = "participanteSelecionado"  id = "participanteSelecionado" name="participanteSelecionado" value = "' +nomeParticipante
							    	+'" checked="checked" ">, ');
					}
					
				}
				
		});
	
});