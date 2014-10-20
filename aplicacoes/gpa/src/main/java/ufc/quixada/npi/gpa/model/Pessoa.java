package ufc.quixada.npi.gpa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "pessoa", uniqueConstraints = @UniqueConstraint(columnNames = {
		"id", "login" }))
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean habilitado;

	@ManyToMany
	@JoinTable(name = "papel_pessoa", joinColumns = @JoinColumn(name = "pessoa_id"), inverseJoinColumns = @JoinColumn(name = "papel_id"))
	private List<Papel> papeis;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<ProjetoPessoa> participantes;

	@OneToMany(mappedBy = "autor")
	private List<Projeto> projetos;

	@OneToMany(mappedBy = "usuario")
	private List<Servidor> servidores;

	private String cpf;

	private String nome;

	private String email;

	public Pessoa() {
		super();
	}

	public Pessoa(String cpf, String nome, String sobrenome, String email,
			String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;

		this.email = email;
		this.password = senha;
	}

	public List<ProjetoPessoa> getProjetosPessoas() {
		return participantes;
	}

	public void setProjetosPessoas(List<ProjetoPessoa> participantes) {
		this.participantes = participantes;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/*
	 * public List<Projeto> getProjetoParticipante() { return projeto; } public
	 * void setProjetoParticipante(List<Projeto> projetoParticipante) {
	 * this.projeto = projetoParticipante; }
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public List<Servidor> getServidores() {
		return servidores;
	}

	public void setServidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pessoa) {
			Pessoa other = (Pessoa) obj;
			if (other != null && other.getId() != null && this.id != null
					&& other.getId().equals(this.id)) {
				return true;
			}
		}
		return false;
	}

}
