package quixada.ufc.br.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import quixada.ufc.br.enumerator.StatusParecer;

@Entity
public class Parecer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private StatusParecer status;
	
	private String comentario;
	
	private String parecer;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dataAtribuicao;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dataRealizacao;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date prazo;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;

	public Parecer(){
		super();
	}
	
	public Parecer(int id, StatusParecer status,
			String comentario, String parecer, Date dataAtribuicao,
			Date dataRealizacao, Date prazo, Usuario usuario, Projeto projeto) {
		super();
		this.id = id;
		this.status = status;
		this.comentario = comentario;
		this.parecer = parecer;
		this.dataAtribuicao = dataAtribuicao;
		this.dataRealizacao = dataRealizacao;
		this.prazo = prazo;
		this.usuario = usuario;
		this.projeto = projeto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StatusParecer getStatus() {
		return status;
	}

	public void setStatus(StatusParecer status) {
		this.status = status;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Date getDataAtribuicao() {
		return dataAtribuicao;
	}

	public void setDataAtribuicao(Date dataAtribuicao) {
		this.dataAtribuicao = dataAtribuicao;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
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
		return "Parecer [id=" + id + ", statusParecer=" + status
				+ ", comentarioDiretor=" + comentario + ", parecer="
				+ parecer + ", dataAtribuicao=" + dataAtribuicao
				+ ", dataRealizacao=" + dataRealizacao + ", prazo=" + prazo
				+ ", usuario=" + usuario + ", projeto=" + projeto + "]";
	}
}
