package ufc.quixada.npi.gpa.service.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ufc.quixada.npi.gpa.model.Comentario;
import ufc.quixada.npi.gpa.service.ComentarioService;

@Repository
@Transactional
public class ComentarioServiceImpl<T> extends GenericServiceImpl<Comentario>
		implements ComentarioService {

}