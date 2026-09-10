package mz.co.gestao.gestao_expedientes.controller;

import mz.co.gestao.gestao_expedientes.entity.Auditoria;
import mz.co.gestao.gestao_expedientes.entity.Despacho;
import mz.co.gestao.gestao_expedientes.entity.Expediente;
import mz.co.gestao.gestao_expedientes.entity.Tramitacao;
import mz.co.gestao.gestao_expedientes.service.AuditoriaService;
import mz.co.gestao.gestao_expedientes.service.DespachoService;
import mz.co.gestao.gestao_expedientes.service.ExpedienteService;
import mz.co.gestao.gestao_expedientes.service.TramitacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RelatorioController {
    private final ExpedienteService expedienteService;
    private final TramitacaoService tramitacaoService;
    private final DespachoService despachoService;
    private final AuditoriaService auditoriaService;

    public RelatorioController(ExpedienteService expedienteService,
                               TramitacaoService tramitacaoService,
                               DespachoService despachoService,
                               AuditoriaService auditoriaService) {
        this.expedienteService = expedienteService;
        this.tramitacaoService = tramitacaoService;
        this.despachoService = despachoService;
        this.auditoriaService = auditoriaService;
    }

    @GetMapping("/relatorios")
    public String relatorios(Model model) {
        List<Expediente> expedientes = expedienteService.listarTodos();
        List<Tramitacao> tramitacoes = tramitacaoService.listarTodos();
        List<Despacho> despachos = despachoService.listarTodos();
        List<Auditoria> auditorias = auditoriaService.listarTodos();
        model.addAttribute("expedientes", expedientes);
        model.addAttribute("tramitacoes", tramitacoes);
        model.addAttribute("despachos", despachos);
        model.addAttribute("auditorias", auditorias);
        return "relatorios";
    }

    @GetMapping("/relatorios/expedientes")
    public String relatorioExpedientes(Model model) {
        List<Expediente> expedientes = expedienteService.listarTodos();
        model.addAttribute("expedientes", expedientes);
        return "relatorio-expedientes";
    }

    @GetMapping("/relatorios/tramitacoes")
    public String relatorioTramitacoes(Model model) {
        List<Tramitacao> tramitacoes = tramitacaoService.listarTodos();
        model.addAttribute("tramitacoes", tramitacoes);
        return "relatorio-tramitacoes";
    }

    @GetMapping("/relatorios/despachos")
    public String relatorioDespachos(Model model) {
        List<Despacho> despachos = despachoService.listarTodos();
        model.addAttribute("despachos", despachos);
        return "relatorio-despachos";
    }

    @GetMapping("/relatorios/auditoria")
    public String relatorioAuditoria(Model model) {
        List<Auditoria> auditorias = auditoriaService.listarTodos();
        model.addAttribute("auditorias", auditorias);
        return "relatorio-auditoria";
    }
}