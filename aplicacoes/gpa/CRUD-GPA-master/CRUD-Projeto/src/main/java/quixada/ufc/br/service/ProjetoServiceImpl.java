package quixada.ufc.br.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import quixada.ufc.br.model.Projeto;
import quixada.ufc.br.repository.GenericRepository;
import quixada.ufc.br.repository.jpa.JpaGenericRepositoryImpl.QueryType;

@Named
public class  ProjetoServiceImpl extends GenericServiceImpl<Projeto> implements ProjetoService{
	@Inject
	private GenericRepository<Projeto> genericRepository;

	@Override
	public int getMAXid() {
		List<Projeto> proj = genericRepository.find(QueryType.JPQL, "from Projeto where id = (select MAX(id) from Projeto)", null);
		if(proj==null || proj.isEmpty()){
			return 1;	
		}
		return proj.get(0).getId();
	}
	
	@Override
	public List<Projeto> projetosSubmetidos(){
		List<Projeto> projeto = genericRepository.find(QueryType.JPQL, "from Projeto as p where p.status='SUBMETIDO' ",null);
		
		return projeto;
		
	}
  
}
