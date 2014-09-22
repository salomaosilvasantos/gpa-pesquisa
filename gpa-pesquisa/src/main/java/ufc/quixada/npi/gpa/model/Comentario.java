package ufc.quixada.npi.gpa.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String texto;
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date data;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Pessoa usuario;
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;

	public Comentario() {
		super();
	}

	public Comentario(Long id, String texto, Date data, Pessoa usuario,
			Projeto projeto) {
		super();
		this.id = id;
		this.texto = texto;
		this.data = data;
		this.usuario = usuario;
		this.projeto = projeto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", texto=" + texto + ", data=" + data
				+ ", usuario=" + usuario + ", projeto=" + projeto + "]";
	}
}