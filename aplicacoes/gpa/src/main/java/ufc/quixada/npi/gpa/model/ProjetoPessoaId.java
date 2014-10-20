package ufc.quixada.npi.gpa.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProjetoPessoaId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Projeto projeto;
	private Pessoa pessoa;

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj); // To change body of generated methods, choose
									// Tools | Templates.
	}

	@Override
	public int hashCode() {
		return super.hashCode(); // To change body of generated methods, choose
									// Tools | Templates.
	}

	public ProjetoPessoaId() {
		super();
	}

}
