package ufc.quixada.npi.gpa.controller;

import static ufc.quixada.npi.gpa.utils.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ufc.quixada.npi.gpa.model.Pessoa;
import ufc.quixada.npi.gpa.model.Projeto;
import ufc.quixada.npi.gpa.model.Projeto.StatusProjeto;
import ufc.quixada.npi.gpa.service.ComentarioService;
import ufc.quixada.npi.gpa.service.DocumentoService;
import ufc.quixada.npi.gpa.service.ParecerService;
import ufc.quixada.npi.gpa.service.ProjetoService;
import ufc.quixada.npi.gpa.service.UsuarioService;
import ufc.quixada.npi.gpa.service.impl.NotificationService;
import ufc.quixada.npi.gpa.utils.Constants;

@Controller
@RequestMapping("projeto")
public class ProjetoController {

	@Inject
	private ProjetoService projetoService;

	@Inject
	private UsuarioService usuarioService;

	@Autowired
	private ComentarioService comentarioService;
	
	@Inject
	private DocumentoService documentoService;

	@Inject
	private ParecerService parecerService;

	@Inject
	private NotificationService notificationService;

	/*public static Properties getProp() throws IOException {
		Resource resource = new ClassPathResource("/notification.properties");
		return PropertiesLoaderUtils.loadProperties(resource);
	}*/
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return REDIRECT_PAGINA_LISTAR_PROJETO;
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model, HttpSession session) {
		Long idUsuarioLogado = getUsuarioLogado(session).getId();
		model.addAttribute("projetos", projetoService.getProjetosByUsuario(idUsuarioLogado));
		model.addAttribute("projetosAguardandoParecer", projetoService.getProjetosAguardandoParecer(idUsuarioLogado));
		model.addAttribute("projetosAvaliados", projetoService.getProjetosAvaliadosDoUsuario(idUsuarioLogado));

		if (usuarioService.isDiretor(getUsuarioLogado(session))) {
			model.addAttribute("projetosSubmetidos", projetoService.getProjetosSubmetidos());
			model.addAttribute("projetosAvaliados", projetoService.getProjetosAvaliados());
			model.addAttribute("participantes", usuarioService.getParticipantesProjetos());
			return PAGINA_LISTAR_PROJETO_DIRETOR;
		}
		return PAGINA_LISTAR_PROJETO;

	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastrarForm(Model model, HttpSession session) {   
		model.addAttribute("projeto", new Projeto());
		model.addAttribute("participantes", usuarioService.getParticipantes(getUsuarioLogado(session)));
		model.addAttribute("action", "cadastrar");
		return PAGINA_CADASTRAR_PROJETO;
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String cadastrar(@RequestParam(value = "idParticipantes", required = false) List<String> idParticipantes, @Valid Projeto projeto, BindingResult result, HttpSession session, RedirectAttributes redirect, Model model) {
		model.addAttribute("participantes", usuarioService.getParticipantes(getUsuarioLogado(session)));
		model.addAttribute("action", "cadastrar");
		if (result.hasErrors()) {
			return PAGINA_CADASTRAR_PROJETO;
		}
		
		projeto.setAutor(getUsuarioLogado(session));
		
		if(idParticipantes != null && !idParticipantes.isEmpty()) {
			List<Pessoa> participantes = new ArrayList<Pessoa>();
			for(String idParticipante : idParticipantes) {
				participantes.add(usuarioService.getUsuarioById(new Long(idParticipante)));
			}
			projeto.setParticipantes(participantes);
		}
		
		Map<String, String> resultado = projetoService.cadastrar(projeto);
		if (!resultado.isEmpty()) {
			buildValidacoes(resultado, result);
			return PAGINA_CADASTRAR_PROJETO;
		}
		
		redirect.addFlashAttribute("info", MENSAGEM_PROJETO_CADASTRADO);
		return REDIRECT_PAGINA_LISTAR_PROJETO;
	}

	/*@RequestMapping(value = "/{id}/detalhes")
	public String getDetalhes(@PathVariable("id") Long id, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Projeto projeto = projetoService.getProjetoById(id);
		if (projeto == null) {
			redirectAttributes.addFlashAttribute("erro", "Projeto inexistente.");
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}
		Pessoa usuario = getUsuarioLogado(session);
		if (usuario.getId() == projeto.getAutor().getId() || usuarioService.isDiretor(usuario)) {
			model.addAttribute("projeto", projeto);
			return PAGINA_DETALHES_PROJETO;
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}
	}*/

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editarForm(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {		
		Projeto projeto = projetoService.getProjetoById(id);
		if (projeto == null) {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_PROJETO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}
		Pessoa usuario = getUsuarioLogado(session);
		if (usuario.getId() == projeto.getAutor().getId() && projeto.getStatus().equals(StatusProjeto.NOVO)) {
			model.addAttribute("projeto", projeto);
			model.addAttribute("participantes", usuarioService.getParticipantes(usuario));
			model.addAttribute("action", "editar");
			return PAGINA_CADASTRAR_PROJETO;
		}

		redirectAttributes.addFlashAttribute("erro", MENSAGEM_PERMISSAO_NEGADA);
		return REDIRECT_PAGINA_LISTAR_PROJETO;
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public String editar(@RequestParam(value = "idParticipantes", required = false) List<String> idParticipantes, @Valid Projeto projeto, BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirect) {
		
		model.addAttribute("participantes", usuarioService.getParticipantes(getUsuarioLogado(session)));
		model.addAttribute("action", "cadastrar");
		if (result.hasErrors()) {
			return PAGINA_CADASTRAR_PROJETO;
		}
		
		projeto.setAutor(getUsuarioLogado(session));
		
		if(idParticipantes != null && !idParticipantes.isEmpty()) {
			List<Pessoa> participantes = new ArrayList<Pessoa>();
			for(String idParticipante : idParticipantes) {
				participantes.add(usuarioService.getUsuarioById(new Long(idParticipante)));
			}
			projeto.setParticipantes(participantes);
		}
		
		Map<String, String> resultado = projetoService.atualizar(projeto);
		if (!resultado.isEmpty()) {
			buildValidacoes(resultado, result);
			return PAGINA_CADASTRAR_PROJETO;
		}
		
		redirect.addFlashAttribute("info", MENSAGEM_PROJETO_ATUALIZADO);
		return REDIRECT_PAGINA_LISTAR_PROJETO;
	}
	
	@RequestMapping(value = "/{id}/excluir")
	public String excluir(@PathVariable("id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
		Projeto projeto = projetoService.getProjetoById(id);
		if (projeto == null) {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_PROJETO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}
		Pessoa usuario = getUsuarioLogado(session);
		if (usuario.getId() == projeto.getAutor().getId() && projeto.getStatus().equals(StatusProjeto.NOVO)) {
			projetoService.remover(projeto);
			redirectAttributes.addFlashAttribute("info", MENSAGEM_PROJETO_REMOVIDO);
		} else {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_PERMISSAO_NEGADA);
		}
		return REDIRECT_PAGINA_LISTAR_PROJETO;

	}
	
	@RequestMapping(value = "{id}/submeter", method = RequestMethod.GET)
	public String submeterForm(@PathVariable("id") Long id, Projeto projeto, BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		projeto = projetoService.getProjetoById(id);

		if (projeto == null) {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_PROJETO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}
		
		Pessoa usuario = getUsuarioLogado(session);
		if (usuario.getId() == projeto.getAutor().getId() && projeto.getStatus().equals(StatusProjeto.NOVO)) {
			Map<String, String> resultadoValidacao = projetoService.submeter(projeto);
			if(resultadoValidacao.isEmpty()) {
				redirectAttributes.addFlashAttribute("info", MENSAGEM_PROJETO_SUBMETIDO);
			}
			model.addAttribute("projeto", projeto);
			model.addAttribute("participantes", usuarioService.getParticipantes(usuario));
			model.addAttribute("alert", MENSAGEM_CAMPO_OBRIGATORIO_SUBMISSAO);
			buildValidacoes(resultadoValidacao, result);
			return PAGINA_SUBMETER_PROJETO;
		} else {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_PERMISSAO_NEGADA);
		}
		
		return REDIRECT_PAGINA_LISTAR_PROJETO;

	}

	@RequestMapping(value = "submeter", method = RequestMethod.POST)
	public String submeter(@RequestParam(value = "idParticipantes", required = false) List<String> idParticipantes, @Valid Projeto projeto, BindingResult result,
			Model model, HttpSession session, RedirectAttributes redirectAttributes) {		
		
		if (result.hasErrors()) {
			return PAGINA_SUBMETER_PROJETO;
		}
		
		projeto.setAutor(getUsuarioLogado(session));
		
		if(idParticipantes != null && !idParticipantes.isEmpty()) {
			List<Pessoa> participantes = new ArrayList<Pessoa>();
			for(String idParticipante : idParticipantes) {
				participantes.add(usuarioService.getUsuarioById(new Long(idParticipante)));
			}
			projeto.setParticipantes(participantes);
		}
		
		
		Map<String, String> resultadoValidacao = projetoService.submeter(projeto);
		if(resultadoValidacao.isEmpty()) {
			redirectAttributes.addFlashAttribute("info", MENSAGEM_PROJETO_SUBMETIDO);
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}
		model.addAttribute("projeto", projeto);
		model.addAttribute("participantes", usuarioService.getParticipantes(getUsuarioLogado(session)));
		buildValidacoes(resultadoValidacao, result);
		return PAGINA_SUBMETER_PROJETO;
	}

	/*@RequestMapping(value = "/{idProjeto}/emitirParecer/{idParecer}", method = RequestMethod.GET)
	public String emitirParecerForm(@PathVariable("idProjeto") long idProjeto,
			@PathVariable("idParecer") long idParecer, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Projeto projeto = projetoService.getProjetoById(idProjeto);
		if (projeto == null) {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_PROJETO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}
		if (!projeto.getStatus().equals(StatusProjeto.AGUARDANDO_PARECER)) {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_PERMISSAO_NEGADA);
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}
		Parecer parecer = parecerService.getParecerById(idParecer);
		if (getUsuarioLogado(session).getId() != parecer.getUsuario().getId()) {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_PERMISSAO_NEGADA);
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}
		model.addAttribute("projeto", projeto);
		return PAGINA_EMITIR_PARECER;
	}*/

	/*@RequestMapping(value = "/{idProjeto}/emitirParecer/{idParecer}", method = RequestMethod.POST)
	public String emitirParecer(@PathVariable("idProjeto") Long idProjeto,
			@PathVariable("idParecer") Long idParecer,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam("parecer") String comentario,
			@RequestParam("statusParecer") String status,
			BindingResult result, RedirectAttributes redirectAttributes){

		Projeto projeto = projetoService.getProjetoById(idProjeto);
		if (projeto == null) {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_PROJETO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}
		Parecer parecer = parecerService.getParecerById(idParecer);
		if (parecer == null) {
			redirectAttributes.addFlashAttribute("erro", MENSAGEM_PROJETO_INEXISTENTE);
			return REDIRECT_PAGINA_LISTAR_PROJETO;
		}

		if (comentario.isEmpty()) {
			redirectAttributes.addAttribute("erro",
					"Comentário não pode estar vazio");
			return "redirect:/projeto/" + idProjeto + "/emitirParecer" + idParecer;
		}

		for (MultipartFile mpf : files) {
			if (mpf.getBytes().length > 0) {
				Documento documento = new Documento();
				documento.setNomeOriginal(mpf.getOriginalFilename());
				documento.setTipo(mpf.getContentType());
				documento.setProjeto(projeto);
				documento.setArquivo(mpf.getBytes());
				documentoService.save(documento);
				parecer.setDocumento(documento);
			}

		}

		if (status.equals("favorável")) {
			parecer.setStatus(StatusPosicionamento.FAVORAVEL);
		} else {
			parecer.setStatus(StatusPosicionamento.NAO_FAVORAVEL);
		}

		parecer.setComentario(comentario);
		parecerService.update(parecer);
		projeto.setStatus(StatusProjeto.AGUARDANDO_AVALIACAO);
		projetoService.update(projeto);

		notificationService.notificar(projeto, Evento.EMISSAO_PARECER);

		return "redirect:/projeto/listar";

	}

	@RequestMapping(value = "/{id}/verParecer", method = RequestMethod.GET)
	public String verParecer(@PathVariable("id") long id, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {

		Projeto projeto = projetoService.find(Projeto.class, id);
		Pessoa usuario = getUsuarioLogado(session);
		// Verifica se o projeto existe
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}
		// Verifica se o usuário logado é o autor do projeto ou se é o diretor e
		// o projeto já foi submetido
		if (usuario.getId() == projeto.getAutor().getId()
				|| (usuarioService.isDiretor(usuario))) {
			model.addAttribute("projeto", projeto);
			return "diretor/verParecer";
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
			return "redirect:/projeto/listar";
		}

	}

	@RequestMapping(value = "/{id}/avaliarProjeto", method = RequestMethod.GET)
	public String avaliarProjeto(@PathVariable("id") long id, Model model,
			HttpSession session, RedirectAttributes redirect) {

		Projeto projeto = projetoService.find(Projeto.class, id);
		
		if (projeto == null) {
			redirect.addFlashAttribute("erro", "Projeto Inexistente.");
			return "redirect:/projeto/listar";
		}

		if (!projeto.getStatus().equals(StatusProjeto.AGUARDANDO_AVALIACAO)) {
			redirect.addFlashAttribute("erro",
					"Projeto não está aguardando avaliação");
			return "redirect:/projeto/listar";
		}
		if (!(usuarioService.isDiretor(getUsuarioLogado(session)))) {
			redirect.addFlashAttribute("erro",
					"Permissão para avaliar projeto negada");
			return "redirect:/projeto/listar";
		}
		projeto.setAvaliacao(new Date());
		model.addAttribute("projeto", projeto);
		return "diretor/avaliarProjeto";
	}

	@RequestMapping(value = "/{id}/avaliarProjeto", method = RequestMethod.POST)
	public String avaliarProjeto(HttpServletRequest request,
			@PathVariable("id") long id,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam("observacao") String observacao,
			@RequestParam("opcoesAvaliacao") String status,
			HttpSession session, RedirectAttributes redirect)
			throws IOException {

		Projeto projeto = projetoService.find(Projeto.class, id);

		if (observacao.isEmpty()) {
			redirect.addAttribute("erro", "Comentário não pode estar vazio");
			return "redirect:/projeto/" + id + "/avaliarProjeto";
		}

		for (MultipartFile mpf : files) {
			if (mpf.getBytes().length > 0) {
				Documento documento = new Documento();
				documento.setNomeOriginal(mpf.getOriginalFilename());
				documento.setTipo(mpf.getContentType());
				documento.setProjeto(projeto);
				documento.setArquivo(mpf.getBytes());
				documentoService.save(documento);
			}

		}
		if (status.equals("Aprovado")) {
			projeto.setStatus(StatusProjeto.APROVADO);
		} else if (status.equals("Aprovado com restrição")) {
			projeto.setStatus(StatusProjeto.APROVADO_COM_RESTRICAO);
		} else {
			projeto.setStatus(StatusProjeto.REPROVADO);
		}

		this.projetoService.save(projeto);

		this.projetoService.update(projeto);
		redirect.addFlashAttribute("info", "Projeto avaliado.");

		return "redirect:/projeto/listar";

	}

	@RequestMapping(value = "diretor/{projetoId}/atribuirParecerista", method = RequestMethod.GET)
	public String atribuirParecerista(
			@PathVariable("projetoId") Long projetoId, Model model,
			RedirectAttributes redirectAttributes) {

		Projeto projeto = projetoService.find(Projeto.class, projetoId);

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
				usuarioService.getPareceristas(projeto.getAutor().getId()));
		return "diretor/atribuirParecerista";
	}

	@RequestMapping(value = "diretor/atribuirParecerista", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(HttpServletRequest request,
			Model model, RedirectAttributes redirect) throws IOException {

		Long projetoId = Long.parseLong(request.getParameter("projetoId"));
		Long parecerista = Long.parseLong(request.getParameter("parecerista"));
		String observacao = request.getParameter("observacao");

		Projeto projeto = projetoService.find(Projeto.class, projetoId);
		redirect.addFlashAttribute("parecerista", parecerista);
		redirect.addFlashAttribute("observacao", observacao);
		redirect.addFlashAttribute("projetoId", projetoId);
		redirect.addFlashAttribute("usuarios",
				usuarioService.getPareceristas(projeto.getAutor().getId()));

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

		Pessoa usuario = usuarioService.find(Pessoa.class, parecerista);
		Parecer parecer = new Parecer();
		parecer.setProjeto(projeto);
		parecer.setUsuario(usuario);
		parecer.setDataAtribuicao(new Date());
		parecer.setComentario(observacao);
		parecer.setPrazo(prazo);

		parecerService.save(parecer);
		projeto.setStatus(StatusProjeto.AGUARDANDO_PARECER);
		projetoService.update(projeto);

		notificationService.notificar(projeto, Evento.ATRIBUICAO_PARECERISTA);

		redirect.addFlashAttribute("info",
				"O parecerista foi atribuído ao projeto com sucesso.");

		return "redirect:/projeto/listar";
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
		if (projeto.getCargaHoraria() == null) {
			model.addAttribute("error_cargaHoraria", "Campo obrigatório");
			valid = false;
		}		
		if (projeto.getValorDaBolsa() == null) {
			model.addAttribute("error_valorDaBolsa", "Campo obrigatório");
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
		
		 * if (projeto.getParticipantes().isEmpty()) {
		 * model.addAttribute("error_participantes", "Campo obrigatório"); valid
		 * = false; }
		 

		return valid;
	}

	private boolean validaSubmissaoAnexo(Projeto projeto, Model model) {
		boolean valid = true;
		if (projeto.getDocumentos().size() == 0) {
			model.addAttribute("error_documento", "Arquivo obrigatório");
			valid = false;
		}

		return valid;
	}*/
	
	private void buildValidacoes(Map<String, String> resultado, BindingResult result) {
		Set<String> keys = resultado.keySet();
		for (String key : keys) {
			result.rejectValue(key, "Repeat.titulo." + key, resultado.get(key));
		}
	}
	
	private Pessoa getUsuarioLogado(HttpSession session) {
		if (session.getAttribute(Constants.USUARIO_LOGADO) == null) {
			Pessoa usuario = usuarioService
					.getUsuarioByLogin(SecurityContextHolder.getContext()
							.getAuthentication().getName());
			session.setAttribute(Constants.USUARIO_LOGADO, usuario);
		}
		return (Pessoa) session.getAttribute(Constants.USUARIO_LOGADO);
	}

}
