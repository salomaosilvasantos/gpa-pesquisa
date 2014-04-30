package quixada.ufc.br.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import quixada.ufc.br.model.Projeto;
import quixada.ufc.br.service.ProjetoService;

@Controller
public class ProjetoController {

	@Inject
	private ProjetoService pc;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		log.info("controller: projeto - action: index");
		return "index";
	}

	@RequestMapping("/cadastro")
	public ModelAndView cadastro() {
		return new ModelAndView("cadastro");
	}
	
	

	@RequestMapping(value = "/novoProjeto", method = RequestMethod.POST)
	public String adicionarProjeto(@Valid Projeto projeto,
			BindingResult result, SessionStatus status,
			HttpServletRequest request) {
		projeto.setStatus("NOVO");
		log.info("controller: projeto - action: AdicionarProjetos");
		if (result.hasErrors()) {
			return "home";
		} else {
			this.pc.salvar(projeto);
			status.setComplete();
			return "redirect:/listar";
		}
	}
	
	@RequestMapping(value="/{id}/editarProjeto")
	public String editar(Projeto p, @PathVariable("id") Integer id, Model model){
		Projeto projeto = pc.findById(id);
		model.addAttribute("editarProjeto", projeto);
		return "editar";
	}
	
	@RequestMapping(value = "/{id}/editarProjetoForm", method=RequestMethod.POST)
	public String atualizarProjeto(@PathVariable("id") Integer id, @ModelAttribute(value="editarProjeto") Projeto projetoAtualizado,BindingResult result){
		this.pc.atualizar(projetoAtualizado);
		return "redirect:/listar";
	}

	@RequestMapping(value="/{id}/excluirProjeto")
	public String excluirProjeto(Projeto p, @PathVariable("id") Integer id, Model model){
		Projeto projeto = pc.findById(id);
		if(projeto == null){
			return "redirect:/listar";
		}
		else{
			this.pc.delete(projeto);
			return "redirect:/listar";
		}
	}
	
	@RequestMapping(value="{id}/submeterProjeto")
	public String submeterProjeto(@PathVariable("id") Integer id){
		Projeto projeto = pc.findById(id);
		projeto.setStatus("SUBMETIDO");
		this.pc.atualizar(projeto);
		
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/listar")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listar");
		List<Projeto> projeto = pc.findAll();
		modelAndView.addObject("projetos", projeto);
		return modelAndView;
	}
}