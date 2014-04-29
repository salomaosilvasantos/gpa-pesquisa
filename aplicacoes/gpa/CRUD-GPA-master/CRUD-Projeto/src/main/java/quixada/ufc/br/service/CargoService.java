package quixada.ufc.br.service;

import java.util.List;

import quixada.ufc.br.model.Cargo;

public interface CargoService {

public abstract void salvar(Cargo cargo);
	
	public abstract void atualizar(Cargo cargo);

	public abstract Cargo findById(int id);

	public abstract List<Cargo> findAll();

	public abstract void delete(Cargo cargo);
}
