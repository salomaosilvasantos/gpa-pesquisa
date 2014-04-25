package quixada.ufc.br.repository.jpa;

import javax.inject.Named;

import quixada.ufc.br.model.Usuario;
import quixada.ufc.br.repository.UsuarioRepository;

@Named
public class JpaUsuarioRepository extends JpaGenericRepositoryImpl<Usuario> implements UsuarioRepository{

	public JpaUsuarioRepository() {
		super();
		this.persistentClass = Usuario.class;
	}

	
}
