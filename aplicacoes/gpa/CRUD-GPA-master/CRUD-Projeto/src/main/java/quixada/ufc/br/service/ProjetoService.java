package quixada.ufc.br.service;

import java.util.List;

import quixada.ufc.br.model.Projeto;

public interface ProjetoService {
	
	public abstract int getMAXid();
	public abstract List<Projeto> projetosSubmetidos();  

}
