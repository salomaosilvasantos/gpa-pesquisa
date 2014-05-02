package quixada.ufc.br.repository.jpa;

import javax.inject.Named;

import quixada.ufc.br.model.Projeto;
import quixada.ufc.br.repository.ProjetoRepository;

@Named
public class JpaProjetoRepository extends JpaGenericRepositoryImpl<Projeto> implements ProjetoRepository {

	public JpaProjetoRepository() {
		super();
		this.persistentClass = Projeto.class;
	}

	
}
