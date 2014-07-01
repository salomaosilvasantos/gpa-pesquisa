package quixada.ufc.br.service;

import java.util.List;

import quixada.ufc.br.model.Projeto;

public interface ProjetoService extends GenericService<Projeto>{
	
	public abstract List<Projeto> projetosSubmetidos();  

}
