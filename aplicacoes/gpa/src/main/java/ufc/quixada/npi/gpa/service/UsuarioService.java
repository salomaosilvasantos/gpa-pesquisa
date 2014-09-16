package ufc.quixada.npi.gpa.service;

import java.util.List;

import ufc.quixada.npi.gpa.model.Pessoa;

public interface UsuarioService extends GenericService<Pessoa> {

	public abstract Pessoa getUsuarioByLogin(String login);

	public abstract List<Pessoa> getPareceristas(Long id);

	public abstract boolean isDiretor(Pessoa usuario);
	
	public abstract Pessoa getDiretor();
	
	public abstract List<Pessoa> getParticipantes();
	
}
