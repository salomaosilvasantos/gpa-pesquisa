package quixada.ufc.br.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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
	
	private Enum<StatusParecer> statusParecer;
	
	private String comentario_diretor;
	
	private String parecer;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date data_atribuicao;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date data_realizacao;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date prazo;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projetos;

	public Parecer(){
		super();
	}
	
	public Parecer(int id, Enum<StatusParecer> statusParecer,
			String comentario_diretor, String parecer, Date data_atribuicao,
			Date data_realizacao, Date prazo, Usuario usuario, Projeto projetos) {
		super();
		this.id = id;
		this.statusParecer = statusParecer;
		this.comentario_diretor = comentario_diretor;
		this.parecer = parecer;
		this.data_atribuicao = data_atribuicao;
		this.data_realizacao = data_realizacao;
		this.prazo = prazo;
		this.usuario = usuario;
		this.projetos = projetos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Enum<StatusParecer> getStatusParecer() {
		return statusParecer;
	}

	public void setStatusParecer(Enum<StatusParecer> statusParecer) {
		this.statusParecer = statusParecer;
	}

	public String getComentario_diretor() {
		return comentario_diretor;
	}

	public void setComentario_diretor(String comentario_diretor) {
		this.comentario_diretor = comentario_diretor;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Date getData_atribuicao() {
		return data_atribuicao;
	}

	public void setData_atribuicao(Date data_atribuicao) {
		this.data_atribuicao = data_atribuicao;
	}

	public Date getData_realizacao() {
		return data_realizacao;
	}

	public void setData_realizacao(Date data_realizacao) {
		this.data_realizacao = data_realizacao;
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

	public Projeto getProjetos() {
		return projetos;
	}

	public void setProjetos(Projeto projetos) {
		this.projetos = projetos;
	}

	@Override
	public String toString() {
		return "Parecer [id=" + id + ", statusParecer=" + statusParecer
				+ ", comentario_diretor=" + comentario_diretor + ", parecer="
				+ parecer + ", data_atribuicao=" + data_atribuicao
				+ ", data_realizacao=" + data_realizacao + ", prazo=" + prazo
				+ ", usuario=" + usuario + ", projetos=" + projetos + "]";
	}
	
}
