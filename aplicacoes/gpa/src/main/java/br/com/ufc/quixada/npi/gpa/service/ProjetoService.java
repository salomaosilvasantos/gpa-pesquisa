package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.com.ufc.quixada.npi.gpa.model.Projeto;

public interface ProjetoService extends GenericService<Projeto> {

	  List<Projeto> getProjetosSubmetidos();
	
	 List<Projeto> getProjetosAtribuidos();

	  List<Projeto> getProjetosByUsuario(Long id);
	
	  List<Projeto> getProjetosAguardandoParecer();

}
