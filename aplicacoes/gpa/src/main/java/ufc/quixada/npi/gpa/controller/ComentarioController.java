package ufc.quixada.npi.gpa.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ufc.quixada.npi.gpa.model.Comentario;
import ufc.quixada.npi.gpa.model.Pessoa;
import ufc.quixada.npi.gpa.model.Projeto;
import ufc.quixada.npi.gpa.service.ComentarioService;
import ufc.quixada.npi.gpa.service.ProjetoService;
import ufc.quixada.npi.gpa.service.UsuarioService;

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
		Pessoa usuario = usuarioService.find(Pessoa.class, idUsuario);
		comentario.setUsuario(usuario);
		
		Long idProjeto = Long.parseLong(request.getParameter("projeto"));
		Projeto projeto = projetoService.find(Projeto.class, idProjeto);
		comentario.setProjeto(projeto);
		
		this.comentarioService.save(comentario);
		
		return "redirect:/projeto/"+idProjeto+"/detalhes";
	}

}
