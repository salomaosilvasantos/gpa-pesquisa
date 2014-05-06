package quixada.ufc.br.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import quixada.ufc.br.repository.ProjetoRepository;


/*
 * Classe que valida o nome do Projeto. 
 * Author: Lucas Araújo.
 */
public class UniqueNomeValidator implements ConstraintValidator<UniqueNome, String>{

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Override
	public void initialize(UniqueNome constraintAnnotation) {
		
	}

	/*
	 * Método incompleto!!!!!!!! 
	 * Implementar método findByNome da classe ProjetoRepository
	 */
	
	@Override
	public boolean isValid(String nomeProjeto, ConstraintValidatorContext context) {	
		if (projetoRepository == null) {
			return true;
		}
		return true;
	}


}
