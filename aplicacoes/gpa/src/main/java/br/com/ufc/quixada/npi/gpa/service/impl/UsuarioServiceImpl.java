package br.com.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Papel;
import br.com.ufc.quixada.npi.gpa.model.Usuario;
import br.com.ufc.quixada.npi.gpa.repository.QueryType;
import br.com.ufc.quixada.npi.gpa.repository.UsuarioRepository;
import br.com.ufc.quixada.npi.gpa.service.UsuarioService;

@Named
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario> implements UsuarioService {

	@Inject
	UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario getUsuarioByLogin(String login) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "login", login);
		Usuario usuariologado = usuarioRepository.find(QueryType.JPQL, "from Usuario where login = :login", params).get(0);
		return usuariologado;
	}

	@Override
	public List<Usuario> getPareceristas(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "id", id);
		List<Usuario> usuarios = usuarioRepository.find(QueryType.JPQL, "from Usuario u where u.id != :id", params);
		return usuarios;
	}

	@Override
	public boolean isDiretor(Usuario usuario) {
		List<Papel> papeis = usuario.getPapeis();
		for(Papel p: papeis){
			if(p.getNome().equals("ROLE_DIRETOR")){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Usuario getDiretor() {		
		Usuario diretor = new Usuario();		
		List<Usuario> usuarios = find(Usuario.class);		
		for (Usuario u: usuarios){
			if(isDiretor(u)){
				diretor = u;
				break;
			}			
		}			
		return diretor;
	}

}
