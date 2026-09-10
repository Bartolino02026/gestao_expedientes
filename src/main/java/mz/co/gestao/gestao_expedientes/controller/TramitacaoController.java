package mz.co.gestao.gestao_expedientes.controller;

import mz.co.gestao.gestao_expedientes.entity.Tramitacao;
import mz.co.gestao.gestao_expedientes.service.TramitacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import mz.co.gestao.gestao_expedientes.entity.Expediente;
import mz.co.gestao.gestao_expedientes.service.ExpedienteService;
import mz.co.gestao.gestao_expedientes.entity.Utilizador;
import mz.co.gestao.gestao_expedientes.service.UtilizadorService;

import java.util.List;

@Controller
public class TramitacaoController {
    private final TramitacaoService tramitacaoService;
    private final ExpedienteService expedienteService;
    private final UtilizadorService utilizadorService;

    public TramitacaoController(
            TramitacaoService tramitacaoService,
            ExpedienteService expedienteService,
            UtilizadorService utilizadorService) {

        this.tramitacaoService = tramitacaoService;
        this.expedienteService = expedienteService;
        this.utilizadorService = utilizadorService;
    }

    @GetMapping("/tramitacoes")
    public String listarTramitacoes(Model model) {

        List<Tramitacao> tramitacoes =
                tramitacaoService.listarTodos();

        List<Expediente> expedientes =
                expedienteService.listarTodos();

        List<Utilizador> utilizadores =
                utilizadorService.listarTodos();

        model.addAttribute("tramitacoes", tramitacoes);
        model.addAttribute("expedientes", expedientes);
        model.addAttribute("utilizadores", utilizadores);

        return "tramitacoes";
    }

    @GetMapping("/tramitacoes/novo")
    public String novaTramitacao(Model model) {

        List<Expediente> expedientes =
                expedienteService.listarTodos();

        List<Utilizador> utilizadores =
                utilizadorService.listarTodos();

        model.addAttribute("tramitacao", new Tramitacao());
        model.addAttribute("expedientes", expedientes);
        model.addAttribute("utilizadores", utilizadores);

        return "nova-tramitacao";
    }

    @PostMapping("/tramitacoes/salvar")
    public String salvarTramitacao(@ModelAttribute Tramitacao tramitacao) {
        tramitacaoService.guardar(tramitacao);
        return "redirect:/tramitacoes";
    }
}
