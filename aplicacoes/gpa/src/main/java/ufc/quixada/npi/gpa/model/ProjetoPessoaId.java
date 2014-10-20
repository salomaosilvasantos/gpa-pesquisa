package ufc.quixada.npi.gpa.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

@Embeddable
public class ProjetoPessoaId implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@JoinColumn(name = "projeto_pessoa")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
