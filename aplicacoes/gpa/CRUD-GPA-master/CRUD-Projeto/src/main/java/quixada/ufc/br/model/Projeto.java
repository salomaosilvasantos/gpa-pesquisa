package quixada.ufc.br.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import quixada.ufc.br.enumerator.StatusProjeto;

@Entity
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String codigo;	

	@Size(min = 2, message = "Mínimo 2 caracteres")
	private String nome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date inicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Future(message = "Somente datas futuras")
	private Date termino;

	@Size(min = 5, message = "Mínimo 5 caracteres")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuarioCriador;
	
	private String atividades;

	@Min(value = 1, message = "Número de bolsas deve ser maior que 1")
	private Integer numero_bolsas;

	private String local;
	
	//@Enumerated(EnumType.STRING)
	//private Enum<StatusProjeto> statusProjeto;

	private String status;
	
	private String participantes;

	
	@OneToMany(mappedBy = "projeto", fetch = FetchType.EAGER, cascade = CascadeType.ALL )	
	private List<Documentos> documentos;

	public Projeto() {
		super();
	}

	public Projeto(int id,String codigo, String nome, Date inicio, Date termino,
			String descricao, String atividades, Integer numero_bolsas,
			String local, String statusProjeto, String participantes, Usuario usuarioCriador) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.inicio = inicio;
		this.termino = termino;
		this.descricao = descricao;
		this.atividades = atividades;
		this.numero_bolsas = numero_bolsas;
		this.local = local;
		this.status = statusProjeto;
		this.participantes = participantes;
		this.usuarioCriador = usuarioCriador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

	public Integer getNumero_bolsas() {
		return numero_bolsas;
	}

	public void setNumero_bolsas(Integer numero_bolsas) {
		this.numero_bolsas = numero_bolsas;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParticipantes() {
		return participantes;
	}

	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}

	public List<Documentos> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documentos> documentos) {
		this.documentos = documentos;
	}
	
	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Projeto [id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", inicio=" + inicio
				+ ", termino=" + termino + ", descricao=" + descricao
				+ ", atividades=" + atividades + ", numero_bolsas="
				+ numero_bolsas + ", local=" + local + ", status=" + status
				+ ", participantes=" + participantes + "]";
	}

}
