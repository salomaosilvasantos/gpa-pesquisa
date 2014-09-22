package ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import ufc.quixada.npi.gpa.model.Comentario;
import ufc.quixada.npi.gpa.repository.ComentarioRepository;

@Named
public class ComentarioRepositoryImpl extends JpaGenericRepositoryImpl<Comentario> implements ComentarioRepository{

}
