package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.com.ufc.quixada.npi.gpa.model.Projeto;

public interface ProjetoService extends GenericService<Projeto> {

	public abstract List<Projeto> getProjetosSubmetidos();

	public abstract List<Projeto> getProjetosByUsuario(Long id);

}
