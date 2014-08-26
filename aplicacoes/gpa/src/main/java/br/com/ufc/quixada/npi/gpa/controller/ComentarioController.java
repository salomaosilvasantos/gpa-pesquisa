package br.com.ufc.quixada.npi.gpa.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ufc.quixada.npi.gpa.model.Comentario;
import br.com.ufc.quixada.npi.gpa.model.Projeto;
import br.com.ufc.quixada.npi.gpa.model.Usuario;
import br.com.ufc.quixada.npi.gpa.service.ComentarioService;
import br.com.ufc.quixada.npi.gpa.service.ProjetoService;
import br.com.ufc.quixada.npi.gpa.service.UsuarioService;

@Controller
@RequestMapping("comentario")
public class ComentarioController {
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private ProjetoService projetoService;
	
	@Inject
	private ComentarioService comentarioService;
	 
	
	@RequestMapping(value = "/comentarProjeto", method = RequestMethod.POST)
	public String adicionar(HttpServletRequest request)
			throws Exception {

		Comentario comentario = new Comentario();
		
		comentario.setTexto(request.getParameter("textocomentario"));
		
		comentario.setData(new Date());
		
		Long idUsuario = Long.parseLong(request.getParameter("usuario"));
		Usuario usuario = usuarioService.find(Usuario.class, idUsuario);
		comentario.setUsuario(usuario);
		
		Long idProjeto = Long.parseLong(request.getParameter("projeto"));
		Projeto projeto = projetoService.find(Projeto.class, idProjeto);
		comentario.setProjeto(projeto);
		
		this.comentarioService.save(comentario);
		
		return "redirect:/projeto/"+idProjeto+"/detalhes";
	}

}
