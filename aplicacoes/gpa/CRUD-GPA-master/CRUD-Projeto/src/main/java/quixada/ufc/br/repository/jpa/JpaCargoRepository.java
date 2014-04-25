package quixada.ufc.br.repository.jpa;

import javax.inject.Named;

import quixada.ufc.br.model.Cargo;
import quixada.ufc.br.repository.CargoRepository;

@Named
public class JpaCargoRepository extends JpaGenericRepositoryImpl<Cargo> implements CargoRepository{

	public JpaCargoRepository() {
		super();
		this.persistentClass = Cargo.class;
	}


}
