package quixada.ufc.br.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Servidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer siap;

	@ManyToOne
	private Usuario usuario;

	public Servidor() {
		super();
	}

	public Servidor(Integer siap, Usuario usuario) {
		super();
		this.siap = siap;
		this.usuario = usuario;
	}

	public Integer getSiap() {
		return siap;
	}

	public void setSiap(Integer siap) {
		this.siap = siap;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
