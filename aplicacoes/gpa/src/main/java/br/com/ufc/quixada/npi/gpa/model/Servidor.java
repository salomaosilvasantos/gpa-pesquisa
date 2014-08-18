package br.com.ufc.quixada.npi.gpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Servidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String siap;

	@ManyToOne
	private Usuario usuario;

	public Servidor() {
		super();
	}

	public Servidor(String siap, Usuario usuario) {
		super();
		this.siap = siap;
		this.usuario = usuario;
	}

	public String getSiap() {
		return siap;
	}

	public void setSiap(String siap) {
		this.siap = siap;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
