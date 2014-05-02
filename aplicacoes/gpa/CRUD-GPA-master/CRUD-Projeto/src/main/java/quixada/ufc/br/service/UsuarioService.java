package quixada.ufc.br.service;

import java.util.List;

import quixada.ufc.br.model.Usuario;

public interface UsuarioService {

public abstract void salvar(Usuario usuario);
	
	public abstract void atualizar(Usuario usuario);

	public abstract Usuario findById(int cpf);

	public abstract List<Usuario> findAll();

	public abstract void delete(Usuario usuario);
}
