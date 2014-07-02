package quixada.ufc.br.service;

import java.util.List;

import quixada.ufc.br.model.Usuario;

public interface UsuarioService extends GenericService<Usuario>{
	
	public abstract Usuario getUsuarioLogado();
	public abstract List<Usuario> getPareceristas(int id);
	public abstract boolean isDiretor(Usuario usuario);
}
