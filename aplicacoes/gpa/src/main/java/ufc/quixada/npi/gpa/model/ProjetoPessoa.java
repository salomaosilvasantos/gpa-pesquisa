package ufc.quixada.npi.gpa.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ProjetoPessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    ProjetoPessoaId pk;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="projeto_id")
    private Projeto projeto;
    
    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;  

    private float cargaHorariaMensal;
    private float valorBolsaMensal;
    
    public void setIdPk(Integer id){
    	pk.setId(1);
    }
    
    public ProjetoPessoa(){
    }

	public ProjetoPessoaId getPk() {
		return pk;
	}

	public void setPk(ProjetoPessoaId pk) {
		this.pk = pk;
	}

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
	
	public float getCargaHorariaMensal() {
		return cargaHorariaMensal;
	}

	public void setCargaHorariaMensal(float cargaHorariaMensal) {
		this.cargaHorariaMensal = cargaHorariaMensal;
	}
	
	public float getValorBolsaMensal() {
		return valorBolsaMensal;
	}

	public void setValorBolsaMensal(float valorBolsaMensal) {
		this.valorBolsaMensal = valorBolsaMensal;
	}  

	@Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.projeto);
        hash = 59 * hash + Objects.hashCode(this.pessoa);
        return hash;
    }
	
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProjetoPessoa other = (ProjetoPessoa) obj;
        if (!Objects.equals(this.projeto, other.projeto)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        return true;
    }

	
    
    

}
