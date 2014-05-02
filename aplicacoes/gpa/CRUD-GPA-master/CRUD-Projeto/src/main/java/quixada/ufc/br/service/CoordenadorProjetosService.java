package quixada.ufc.br.service;

import java.util.List;

import quixada.ufc.br.model.CoordenadorProjetos;

public interface CoordenadorProjetosService {

public abstract void salvar(CoordenadorProjetos coordenador);
	
	public abstract void atualizar(CoordenadorProjetos coordenador);

	public abstract CoordenadorProjetos findById(int siap);

	public abstract List<CoordenadorProjetos> findAll();

	public abstract void delete(CoordenadorProjetos contato);
}
