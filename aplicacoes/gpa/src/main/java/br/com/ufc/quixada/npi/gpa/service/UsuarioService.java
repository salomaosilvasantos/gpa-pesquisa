package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;

import br.com.ufc.quixada.npi.gpa.model.Usuario;

public interface UsuarioService extends GenericService<Usuario> {

	 Usuario getUsuarioByLogin(String login);

	 List<Usuario> getPareceristas(Long id);

	 boolean isDiretor(Usuario usuario);
	
	 Usuario getDiretor();
}
