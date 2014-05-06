package quixada.ufc.br.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
/*
 * Anotação usada na variável nome da classe Projeto que tem como função validar se o nome do projeto é Unique
 * retornando uma mensagem no formulário de cadastro informando que o nome digitado ja tem no banco.
 * Author: Lucas Araújo.
 */

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueNomeValidator.class})
public @interface UniqueNome {
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
