package quixada.ufc.br.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date inicio;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date termino;
	private String descricao;
	private String atividades;
	private Integer numero_bolsas;
	private String local;
	private String status;
	private String participantes;
	
	@ManyToMany
	@JoinTable(name = "projeto_usuario", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "projeto_id"))
	private List<Usuario> usuarios;
	
	public Projeto(){
		super();
	}
	public Projeto(Integer id, String nome, Date inicio, Date termino,
			String descricao, String atividades, Integer numero_bolsas,
			String local, String status, String participantes) {
		super();
		this.id = id;
		this.nome = nome;
		this.inicio = inicio;
		this.termino = termino;
		this.descricao = descricao;
		this.atividades = atividades;
		this.numero_bolsas = numero_bolsas;
		this.local = local;
		this.status = status;
		this.participantes = participantes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Projeto [id=" + id + ", nome=" + nome + ", inicio=" + inicio
				+ ", termino=" + termino + ", descricao=" + descricao
				+ ", atividades=" + atividades + ", numero_bolsas="
				+ numero_bolsas + ", local=" + local + ", status=" + status
				+ ", participantes=" + participantes + ", usuarios=" + usuarios
				+ "]";
	}
	
}
