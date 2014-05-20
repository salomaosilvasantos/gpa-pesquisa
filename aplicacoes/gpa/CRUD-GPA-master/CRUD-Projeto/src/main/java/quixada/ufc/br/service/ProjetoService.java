package quixada.ufc.br.service;

import java.util.List;

import quixada.ufc.br.model.Projeto;

public interface ProjetoService {

public abstract void salvar(Projeto projeto);
	
	public abstract void atualizar(Projeto contato);

	public abstract Projeto findById(String id);

	public abstract List<Projeto> findAll();

	public abstract void delete(Projeto projeto);
}
