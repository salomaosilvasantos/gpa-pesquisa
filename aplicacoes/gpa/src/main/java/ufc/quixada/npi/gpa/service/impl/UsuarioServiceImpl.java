package ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ufc.quixada.npi.gpa.model.Papel;
import ufc.quixada.npi.gpa.model.Pessoa;
import ufc.quixada.npi.gpa.repository.QueryType;
import ufc.quixada.npi.gpa.repository.UsuarioRepository;
import ufc.quixada.npi.gpa.service.UsuarioService;

@Named
public class UsuarioServiceImpl extends GenericServiceImpl<Pessoa> implements
		UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepository;

	@Override
	public Pessoa getUsuarioByLogin(String login) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("login", login);
		return usuarioRepository.find(QueryType.JPQL,
				"from Pessoa where login = :login", params).get(0);
	}

	@Override
	public List<Pessoa> getPareceristas(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return usuarioRepository.find(QueryType.JPQL,
				"from Pessoa u where u.id != :id", params);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isDiretor(Pessoa usuario) {
		List<Papel> papeis = usuario.getPapeis();
		for (Papel p : papeis) {
			if (p.getNome().equals("ROLE_DIRETOR")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Pessoa getDiretor() {
		Pessoa diretor = new Pessoa();
		List<Pessoa> usuarios = find(Pessoa.class);
		for (Pessoa u : usuarios) {
			if (isDiretor(u)) {
				diretor = u;
				break;
			}
		}
		return diretor;
	}

	
	@Override
	public List<Pessoa> getParticipantes(Pessoa usuario) {

		List<Pessoa> participantes = usuarioRepository.find(Pessoa.class);
		participantes.remove(usuario);
		
		return participantes;
	}

	@Override
	public List<Pessoa> getParticipantesProjetos() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Pessoa> participantes = usuarioRepository.find(QueryType.JPQL,
				"select distinct proj.participantes from Projeto proj", params);
		return participantes;
	}

	


	@Override
	public Pessoa getPessoaByNome(String nome) {

		Pessoa pessoa = null;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nome", nome);
		List<Pessoa> listaPessoas = usuarioRepository.find(QueryType.JPQL,
				"from Pessoa where nome = :nome", params);

		if (!listaPessoas.isEmpty()) {

			pessoa = listaPessoas.get(0);
		}

		return pessoa;

	}

}
