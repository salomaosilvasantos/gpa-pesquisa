package quixada.ufc.br.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import quixada.ufc.br.model.Projeto;
import quixada.ufc.br.repository.ProjetoRepository;
import quixada.ufc.br.repository.jpa.JpaGenericRepositoryImpl.QueryType;

@Named
public class  ProjetoServiceImpl extends GenericServiceImpl<Projeto> implements ProjetoService{
	@Inject
	private ProjetoRepository projetoRepository;
	
	@Override
	public List<Projeto> projetosSubmetidos(){
		List<Projeto> projeto = projetoRepository.find(QueryType.JPQL, "from Projeto as p where p.status='SUBMETIDO' ",null);
		
		return projeto;
		
	}
  
}
