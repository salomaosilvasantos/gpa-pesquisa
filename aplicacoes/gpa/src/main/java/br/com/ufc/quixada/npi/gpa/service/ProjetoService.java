package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.com.ufc.quixada.npi.gpa.model.Projeto;

public interface ProjetoService extends GenericService<Projeto> {

	 abstract List<Projeto> getProjetosSubmetidos();
	
	 List<Projeto> getProjetosAtribuidos();

	 abstract List<Projeto> getProjetosByUsuario(Long id);
	
	 abstract List<Projeto> getProjetosAguardandoParecer();

}
