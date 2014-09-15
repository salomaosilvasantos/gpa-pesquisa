package ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import ufc.quixada.npi.gpa.model.Pessoa;
import ufc.quixada.npi.gpa.repository.UsuarioRepository;

@Named
public class JpaUsuarioRepositoryImpl extends JpaGenericRepositoryImpl<Pessoa> implements UsuarioRepository{

}
