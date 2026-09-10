package mz.co.gestao.gestao_expedientes.controller;

import mz.co.gestao.gestao_expedientes.entity.Auditoria;
import mz.co.gestao.gestao_expedientes.service.AuditoriaService;
import mz.co.gestao.gestao_expedientes.entity.Expediente;
import mz.co.gestao.gestao_expedientes.service.ExpedienteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import mz.co.gestao.gestao_expedientes.entity.Tramitacao;
import mz.co.gestao.gestao_expedientes.service.TramitacaoService;
import mz.co.gestao.gestao_expedientes.service.UtilizadorService;
import mz.co.gestao.gestao_expedientes.service.AuditoriaService;
import mz.co.gestao.gestao_expedientes.entity.Utilizador;
import mz.co.gestao.gestao_expedientes.service.DespachoService;
import mz.co.gestao.gestao_expedientes.entity.Despacho;
import mz.co.gestao.gestao_expedientes.entity.Expediente;

import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExpedienteController {

    private final ExpedienteService expedienteService;
    private final TramitacaoService tramitacaoService;
    private final UtilizadorService utilizadorService;
    private final DespachoService despachoService;
    private final AuditoriaService auditoriaService;

    public ExpedienteController(
            ExpedienteService expedienteService,
            TramitacaoService tramitacaoService,
            UtilizadorService utilizadorService,
            DespachoService despachoService,
            AuditoriaService auditoriaService) {
        this.expedienteService = expedienteService;
        this.tramitacaoService = tramitacaoService;
        this.utilizadorService = utilizadorService;
        this.despachoService = despachoService;
        this.auditoriaService = auditoriaService;
    }

    @GetMapping("/expedientes")
    public String listarExpedientes(Model model) {

        List<Expediente> expedientes = expedienteService.listarTodos();

        model.addAttribute("expedientes", expedientes);

        return "expedientes";
    }

    @GetMapping("/expedientes/novo")
    public String novoExpediente(Model model) {

        Expediente expediente = new Expediente();

        expediente.setDataEntrada(LocalDate.now());
        expediente.setEstado("RECEBIDO");

        model.addAttribute("expediente", expediente);

        return "novo-expediente";
    }

    @PostMapping("/expedientes/salvar")
    public String salvarExpediente(@ModelAttribute Expediente expediente) {
        expedienteService.guardar(expediente);
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        Utilizador utilizador = utilizadorService.buscarPorUsername(username);
        if (utilizador != null) {
            Auditoria auditoria = new Auditoria();
            auditoria.setUtilizadorId(utilizador.getId());
            auditoria.setAccao("CRIAR_EXPEDIENTE");
            auditoria.setDescricao("Criou o expediente número '" + expediente.getNumero() + "'.");
            auditoriaService.guardar(auditoria);
        }
        return "redirect:/expedientes";
    }

    @PostMapping("/expedientes/editar")
    public String editarExpediente(@ModelAttribute Expediente expediente) {
        expedienteService.guardar(expediente);
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        Utilizador utilizador = utilizadorService.buscarPorUsername(username);
        if (utilizador != null) {
            Auditoria auditoria = new Auditoria();
            auditoria.setUtilizadorId(utilizador.getId());
            auditoria.setAccao("EDITAR_EXPEDIENTE");
            auditoria.setDescricao("Editou o expediente número '" + expediente.getNumero() + "'.");
            auditoriaService.guardar(auditoria);
        }
        return "redirect:/expedientes";
    }

    @GetMapping("/expedientes/visualizar/{id}")
    public String visualizarExpediente(@PathVariable Long id, Model model) {
        Expediente expediente = expedienteService.buscarPorId(id);
        List<Tramitacao> tramitacoes = tramitacaoService.listarPorExpediente(id);
        List<Utilizador> utilizadores = utilizadorService.listarTodos();
        List<Despacho> despachos = despachoService.listarPorExpediente(id);
        model.addAttribute("expediente", expediente);
        model.addAttribute("tramitacoes", tramitacoes);
        model.addAttribute("utilizadores", utilizadores);
        model.addAttribute("despachos", despachos);
        return "visualizar-expediente";
    }

    @GetMapping("/expedientes/editar/{id}")
    public String editarExpediente(@PathVariable Long id, Model model) {
        Expediente expediente = expedienteService.buscarPorId(id);
        model.addAttribute("expediente", expediente);
        return "editar-expediente";
    }

    @GetMapping("/expedientes/eliminar/{id}")
    public String eliminarExpediente(@PathVariable Long id) {
        Expediente expediente = expedienteService.buscarPorId(id);
        if (expediente != null) {
            String numero = expediente.getNumero();
            tramitacaoService.eliminarPorExpediente(id);
            despachoService.eliminarPorExpediente(id);
            expedienteService.eliminar(id);
            String username = org.springframework.security.core.context.SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();
            Utilizador utilizador = utilizadorService.buscarPorUsername(username);
            if (utilizador != null) {
                Auditoria auditoria = new Auditoria();
                auditoria.setUtilizadorId(utilizador.getId());
                auditoria.setAccao("ELIMINAR_EXPEDIENTE");
                auditoria.setDescricao("Eliminou o expediente número '" + numero + "'.");
                auditoriaService.guardar(auditoria);
            }
        }
        return "redirect:/expedientes";
    }
}