package quixada.ufc.br.repository.jpa;

import javax.inject.Named;

import quixada.ufc.br.model.Usuario;
import quixada.ufc.br.repository.UsuarioRepository;

@Named
public class JpaUsuarioRepositoryImpl extends JpaGenericRepositoryImpl<Usuario> implements UsuarioRepository{

}
