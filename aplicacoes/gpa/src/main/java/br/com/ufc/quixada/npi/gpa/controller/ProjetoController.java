package br.com.ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ufc.quixada.npi.gpa.enumerator.StatusParecer;
import br.com.ufc.quixada.npi.gpa.enumerator.StatusProjeto;
import br.com.ufc.quixada.npi.gpa.model.Documento;
import br.com.ufc.quixada.npi.gpa.model.Parecer;
import br.com.ufc.quixada.npi.gpa.model.Projeto;
import br.com.ufc.quixada.npi.gpa.model.Usuario;
import br.com.ufc.quixada.npi.gpa.service.DocumentoService;
import br.com.ufc.quixada.npi.gpa.service.ParecerService;
import br.com.ufc.quixada.npi.gpa.service.ProjetoService;
import br.com.ufc.quixada.npi.gpa.service.UsuarioService;
import br.com.ufc.quixada.npi.gpa.utils.Constants;

@Controller
@RequestMapping("projeto")
public class ProjetoController {

	@Inject
	private ProjetoService serviceProjeto;

	@Inject
	private UsuarioService serviceUsuario;

	@Inject
	private DocumentoService serviceDocumento;

	@Inject
	private ParecerService serviceParecer;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "redirect:/projeto/listar";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("projeto", new Projeto());
		return "projeto/cadastrar";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String adicionarProjeto(
			@Valid @ModelAttribute("projeto") Projeto projeto,
			BindingResult result, HttpSession session,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ("projeto/cadastrar");
		}
			
		
		projeto.setAutor(getUsuarioLogado(session));
		projeto.setStatus(StatusProjeto.NOVO);
		this.serviceProjeto.save(projeto);

		String codigo = geraCodigoProjeto(projeto.getId());
		projeto.setCodigo(codigo);
		this.serviceProjeto.update(projeto);
		redirect.addFlashAttribute("info", "Projeto cadastrado com sucesso.");

		return "redirect:/projeto/listar";
		

	}

	@RequestMapping(value = "/{id}/detalhes")
	public String getDetalhes(Projeto p, @PathVariable("id") Long id,
			Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		Usuario usuario = getUsuarioLogado(session);
		// Verifica se o projeto existe
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}
		// Verifica se o usuário logado é o autor do projeto ou se é o diretor e
		// o projeto já foi submetido
		if (usuario.getId() == projeto.getAutor().getId()
				|| (serviceUsuario.isDiretor(usuario) && !projeto.getStatus()
						.equals(StatusProjeto.NOVO))) {
			model.addAttribute("projeto", projeto);
			return "projeto/detalhes";
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
			return "redirect:/projeto/listar";
		}
	}

	@RequestMapping(value = "/{id}/emitirParecer", method = RequestMethod.GET)
	public String getEmitirParecerPage(@PathVariable("id") long id, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
			if(projeto == null){
				redirectAttributes.addFlashAttribute("erro", "Projeto inexistente.");
		return "redirect:/projeto/listar";
			}
			model.addAttribute("projeto", projeto);
		return "projeto/emitirParecer";
	}
	
	@RequestMapping(value = "/{id}/emitirParecer", method = RequestMethod.POST)
	public String emitirParecer(HttpServletRequest request, @PathVariable("id") long id, 
			@RequestParam("file") MultipartFile[] files,
		Model model,BindingResult result,
		HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
		
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		if(result.hasErrors()){
			return "redirect:/projeto/listar";
		}
		
		Usuario usuario = getUsuarioLogado(session);
		if(usuario.getId() == projeto.getAutor().getId()){
			return "";
			
		}else{
			redirectAttributes.addFlashAttribute("erro", "Não tem permissão para acessar o projeto");
			return "redirect:/projeto/listar";
		}
		
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") long id, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		Usuario usuario = getUsuarioLogado(session);
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}
		if (usuario.getId() == projeto.getAutor().getId()
				&& !projeto.getStatus()
						.equals(StatusProjeto.AGUARDANDO_PARECER)) {
			model.addAttribute("projeto", projeto);
			model.addAttribute("action", "editar");
			return "projeto/editar";
		}

		redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
		return "redirect:/projeto/listar";
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.POST)
	public String atualizarProjeto(
			@PathVariable("id") Long id,
			@RequestParam("file") MultipartFile[] files,
			@Valid @ModelAttribute(value = "projeto") Projeto projetoAtualizado,
			BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("action", "editar");
			return "projeto/editar";
		}
		if (projetoAtualizado.getTermino() != null
				&& comparaDatas(new Date(), projetoAtualizado.getTermino()) > 0) {
			result.rejectValue("termino", "error.projeto",
					"Somente data futura");
			model.addAttribute("action", "editar");
			return "projeto/editar";
		}
		if (projetoAtualizado.getTermino() != null
				&& projetoAtualizado.getInicio() != null
				&& comparaDatas(projetoAtualizado.getInicio(),
						projetoAtualizado.getTermino()) > 0) {
			result.rejectValue("inicio", "error.projeto",
					"A data de início deve ser antes da data de término.");
			model.addAttribute("action", "editar");
			return "projeto/editar";
		}

		Projeto projeto = serviceProjeto.find(Projeto.class, id);

		for (MultipartFile mpf : files) {
			if (mpf.getBytes().length > 0) {
				Documento documento = new Documento();
				documento.setNomeOriginal(mpf.getOriginalFilename());
				documento.setTipo(mpf.getContentType());
				documento.setProjeto(projeto);
				documento.setArquivo(mpf.getBytes());

				serviceDocumento.save(documento);
			}
		}

		projeto.setNome(projetoAtualizado.getNome());
		projeto.setDescricao(projetoAtualizado.getDescricao());
		projeto.setInicio(projetoAtualizado.getInicio());
		projeto.setTermino(projetoAtualizado.getTermino());
		projeto.setAtividades(projetoAtualizado.getAtividades());
		projeto.setQuantidadeBolsa(projetoAtualizado.getQuantidadeBolsa());
		projeto.setLocal(projetoAtualizado.getLocal());
		projeto.setParticipantes(projetoAtualizado.getParticipantes());

		this.serviceProjeto.update(projeto);
		redirect.addFlashAttribute("info", "Projeto atualizado com sucesso.");
		return "redirect:/projeto/listar";
	}

	@RequestMapping(value = "/{id}/excluir")
	public String excluirProjeto(Projeto p, @PathVariable("id") Long id,
			HttpSession session, RedirectAttributes redirectAttributes,
			Model model) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}
		Usuario usuario = getUsuarioLogado(session);
		if (usuario.getId() == projeto.getAutor().getId()
				&& projeto.getStatus().equals(StatusProjeto.NOVO)) {
			this.serviceProjeto.delete(projeto);
			redirectAttributes.addFlashAttribute("info",
					"Projeto excluído com sucesso.");
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
		}
		return "redirect:/projeto/listar";

	}

	@RequestMapping(value = "{id}/submeter", method = RequestMethod.GET)
	public String submeterProjetoForm(@PathVariable("id") Long id, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		Usuario usuario = getUsuarioLogado(session);
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}

		if (usuario.getId() == projeto.getAutor().getId()
				&& projeto.getStatus().equals(StatusProjeto.NOVO)) {
			if (validaSubmissao(projeto, model)) {
				projeto.setStatus(StatusProjeto.SUBMETIDO);
				this.serviceProjeto.update(projeto);
				redirectAttributes.addFlashAttribute("info",
						"Projeto submetido com sucesso.");
				return "redirect:/projeto/listar";
			} else {
				model.addAttribute("projeto", projeto);
				model.addAttribute("action", "submeter");
				return "projeto/editar";
			}

		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
			return "redirect:/projeto/listar";
		}

	}

	@RequestMapping(value = "submeter", method = RequestMethod.POST)
	public String submeterProjeto(
			@ModelAttribute(value = "projeto") Projeto proj,
			BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Projeto projeto = serviceProjeto.find(Projeto.class, proj.getId());
		Usuario usuario = getUsuarioLogado(session);
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}

		if (usuario.getId() == projeto.getAutor().getId()
				&& projeto.getStatus().equals(StatusProjeto.NOVO)) {
			if (validaSubmissao(proj, model)) {
				projeto.setNome(proj.getNome());
				projeto.setDescricao(proj.getDescricao());
				projeto.setInicio(proj.getInicio());
				projeto.setTermino(proj.getTermino());
				projeto.setAtividades(proj.getAtividades());
				projeto.setQuantidadeBolsa(proj.getQuantidadeBolsa());
				projeto.setLocal(proj.getLocal());
				projeto.setParticipantes(proj.getParticipantes());
				projeto.setStatus(StatusProjeto.SUBMETIDO);
				this.serviceProjeto.update(projeto);
				redirectAttributes.addFlashAttribute("info",
						"Projeto submetido com sucesso.");
				return "redirect:/projeto/listar";
			} else {
				model.addAttribute("action", "submeter");
				return "projeto/editar";
			}

		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
			return "redirect:/projeto/listar";
		}

	}

	@RequestMapping(value = "/listar")
	public String listar(ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute("projetos", serviceProjeto
				.getProjetosByUsuario(getUsuarioLogado(session).getId()));
		if (serviceUsuario.isDiretor(getUsuarioLogado(session))) {
			modelMap.addAttribute("projetosSubmetidos",
					serviceProjeto.getProjetosSubmetidos());
			return "diretor/listarProjetos";
		}
		return "projeto/listar";

	}

	@RequestMapping(value = "diretor/{projetoId}/atribuirParecerista", method = RequestMethod.GET)
	public String atribuirParecerista(
			@PathVariable("projetoId") Long projetoId, Model model,
			RedirectAttributes redirectAttributes) {

		Projeto projeto = serviceProjeto.find(Projeto.class, projetoId);

		if (projeto == null) {
			redirectAttributes.addFlashAttribute("erro",
					"O projeto não existe!");
			return "redirect:/projeto/listar";
		}
		if (projeto.getStatus() != StatusProjeto.SUBMETIDO) {
			redirectAttributes.addFlashAttribute("erro",
					"Não é possível atribuir um parecerista a este projeto.");
			return "redirect:/projeto/listar";
		}

		model.addAttribute("projetoId", projetoId);
		model.addAttribute("usuarios",
				serviceUsuario.getPareceristas(projeto.getAutor().getId()));
		return "diretor/atribuirParecerista";
	}

	@RequestMapping(value = "diretor/atribuirParecerista", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(HttpServletRequest request,
			Model model, RedirectAttributes redirect) {

		Long projetoId = Long.parseLong(request.getParameter("projetoId"));
		Long parecerista = Long.parseLong(request.getParameter("parecerista"));
		String comentario = request.getParameter("comentario");

		Projeto projeto = serviceProjeto.find(Projeto.class, projetoId);
		redirect.addFlashAttribute("parecerista", parecerista);
		redirect.addFlashAttribute("comentario", comentario);
		redirect.addFlashAttribute("projetoId", projetoId);
		redirect.addFlashAttribute("usuarios",
				serviceUsuario.getPareceristas(projeto.getAutor().getId()));

		if (request.getParameter("prazo") == null
				|| request.getParameter("prazo").isEmpty()) {
			redirect.addFlashAttribute("error_prazo", "Campo obrigatório");
			return "redirect:/projeto/diretor/" + projetoId
					+ "/atribuirParecerista";
		}

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date prazo = null;
		try {
			prazo = format.parse(request.getParameter("prazo"));
		} catch (ParseException e) {
			redirect.addFlashAttribute("error_prazo", "Data inválida");
			return "redirect:/projeto/diretor/" + projetoId
					+ "/atribuirParecerista";
		}

		if (comparaDatas(prazo, new Date()) < 0) {
			redirect.addFlashAttribute("error_prazo",
					"O prazo não pode ser uma data passada");
			redirect.addFlashAttribute("prazo", request.getParameter("prazo"));
			return "redirect:/projeto/diretor/" + projetoId
					+ "/atribuirParecerista";
		}

		Usuario usuario = serviceUsuario.find(Usuario.class, parecerista);
		Parecer parecer = new Parecer();
		parecer.setProjeto(projeto);
		parecer.setUsuario(usuario);
		parecer.setDataAtribuicao(new Date());
		parecer.setComentario(comentario);
		parecer.setPrazo(prazo);

		serviceParecer.save(parecer);
		projeto.setStatus(StatusProjeto.AGUARDANDO_PARECER);
		serviceProjeto.update(projeto);
		redirect.addFlashAttribute("info",
				"O parecerista foi atribuído ao projeto com sucesso.");

		return "redirect:/projeto/listar";
	}

	private Usuario getUsuarioLogado(HttpSession session) {
		if (session.getAttribute(Constants.USUARIO_LOGADO) == null) {
			Usuario usuario = serviceUsuario
					.getUsuarioByLogin(SecurityContextHolder.getContext()
							.getAuthentication().getName());
			session.setAttribute(Constants.USUARIO_LOGADO, usuario);
		}
		return (Usuario) session.getAttribute(Constants.USUARIO_LOGADO);
	}

	private String geraCodigoProjeto(Long id) {
		if (id < 10) {
			return "PESQ0" + id;
		} else {
			return "PESQ" + id;
		}
	}

	private boolean validaSubmissao(Projeto projeto, Model model) {
		boolean valid = true;

		if (projeto.getNome().isEmpty()) {
			model.addAttribute("error_nome", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getInicio() == null) {
			model.addAttribute("error_inicio", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getTermino() == null) {
			model.addAttribute("error_termino", "Campo obrigatório");
			valid = false;
		} else if (comparaDatas(new Date(), projeto.getTermino()) > 0) {
			model.addAttribute("error_termino", "Somente data futura");
			valid = false;
		}
		if (projeto.getInicio() != null && projeto.getTermino() != null
				&& comparaDatas(projeto.getInicio(), projeto.getTermino()) > 0) {
			model.addAttribute("error_inicio",
					"A data de início deve ser antes da data de término");
			valid = false;
		}
		if (projeto.getDescricao().isEmpty()) {
			model.addAttribute("error_descricao", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getAtividades().isEmpty()) {
			model.addAttribute("error_atividades", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getQuantidadeBolsa() == null) {
			model.addAttribute("error_quantidadeBolsa", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getLocal().isEmpty()) {
			model.addAttribute("error_local", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getParticipantes().isEmpty()) {
			model.addAttribute("error_participantes", "Campo obrigatório");
			valid = false;
		}

		return valid;
	}

	private int comparaDatas(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		if (calendar1.get(Calendar.YEAR) > calendar2.get(Calendar.YEAR)) {
			return 1;
		} else if (calendar1.get(Calendar.YEAR) < calendar2.get(Calendar.YEAR)) {
			return -1;
		} else {
			if (calendar1.get(Calendar.MONTH) > calendar2.get(Calendar.MONTH)) {
				return 1;
			} else if (calendar1.get(Calendar.MONTH) < calendar2
					.get(Calendar.MONTH)) {
				return -1;
			} else {
				if (calendar1.get(Calendar.DAY_OF_MONTH) > calendar2
						.get(Calendar.DAY_OF_MONTH)) {
					return 1;
				} else if (calendar1.get(Calendar.DAY_OF_MONTH) < calendar2
						.get(Calendar.DAY_OF_MONTH)) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}


}
