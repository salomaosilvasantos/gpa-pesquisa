package quixada.ufc.br.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;

import quixada.ufc.br.model.Papel;
import quixada.ufc.br.model.Usuario;
import quixada.ufc.br.repository.UsuarioRepository;
import quixada.ufc.br.repository.jpa.JpaGenericRepositoryImpl.QueryType;

@Named
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario> implements UsuarioService{

	@Inject
	UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario getUsuarioLogado() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "login", SecurityContextHolder.getContext().getAuthentication().getName() );
		Usuario usuariologado = usuarioRepository.find(QueryType.JPQL, "from Usuario where login=:login", params).get(0);
		return usuariologado;
	}

	@Override
	public List<Usuario> getPareceristas(int id) {
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

}
