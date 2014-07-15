package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.com.ufc.quixada.npi.gpa.model.Usuario;

public interface UsuarioService extends GenericService<Usuario> {

	public abstract Usuario getUsuarioByLogin(String login);

	public abstract List<Usuario> getPareceristas(Long id);

	public abstract boolean isDiretor(Usuario usuario);
}
