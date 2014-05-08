$(document).ready(function() {
	$(".status").each(function(){
		if($(this).text() == 'SUBMETIDO'){
		   $(this).parent().find(".botaoBloqueado").prop("disabled", true);
		   $(this).parent().find('.acoes').text('PROJETO SUBMETIDO, AGUARDE PROCESSAMENTO');
		}
	});
});


function submeter(id){
	var confirmarSubmissao = confirm("Deseja submeter?");
		if(!confirmarSubmissao){
			document.getElementById("submeter").href="";
		}
	}