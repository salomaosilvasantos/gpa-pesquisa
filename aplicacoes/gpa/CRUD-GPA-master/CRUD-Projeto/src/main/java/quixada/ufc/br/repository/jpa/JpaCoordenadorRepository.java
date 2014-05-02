package quixada.ufc.br.repository.jpa;

import javax.inject.Named;

import quixada.ufc.br.model.CoordenadorProjetos;
import quixada.ufc.br.repository.CoordenadorProjetosRepository;
@Named
public class JpaCoordenadorRepository extends JpaGenericRepositoryImpl<CoordenadorProjetos> implements CoordenadorProjetosRepository{

	public JpaCoordenadorRepository() {
		super();
		this.persistentClass = CoordenadorProjetos.class;
	}

	
}
