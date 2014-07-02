package quixada.ufc.br.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import quixada.ufc.br.model.Projeto;
import quixada.ufc.br.repository.ProjetoRepository;
import quixada.ufc.br.repository.jpa.JpaGenericRepositoryImpl.QueryType;

@Named
public class  ProjetoServiceImpl extends GenericServiceImpl<Projeto> implements ProjetoService{
	
	@Inject
	private ProjetoRepository projetoRepository;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Override
	public List<Projeto> projetosSubmetidos(){
		List<Projeto> projeto = projetoRepository.find(QueryType.JPQL, "from Projeto as p where p.status !='NOVO' ",null);
		
		return projeto;
		
	}

	@Override
	public List<Projeto> getProjetosUsuario() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "usuario", usuarioService.getUsuarioLogado());
		List<Projeto> projeto = projetoRepository.find(QueryType.JPQL, "from Projeto where usuario_id=:usuario", params);
		return projeto;
	}
  
}
