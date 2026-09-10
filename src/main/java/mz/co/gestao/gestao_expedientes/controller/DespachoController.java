package mz.co.gestao.gestao_expedientes.controller;

import mz.co.gestao.gestao_expedientes.entity.Despacho;
import mz.co.gestao.gestao_expedientes.entity.Expediente;
import mz.co.gestao.gestao_expedientes.entity.Utilizador;
import mz.co.gestao.gestao_expedientes.service.DespachoService;
import mz.co.gestao.gestao_expedientes.service.ExpedienteService;
import mz.co.gestao.gestao_expedientes.service.UtilizadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DespachoController {
    private final DespachoService despachoService;
    private final ExpedienteService expedienteService;
    private final UtilizadorService utilizadorService;

    public DespachoController(DespachoService despachoService, ExpedienteService expedienteService, UtilizadorService utilizadorService) {
        this.despachoService = despachoService;
        this.expedienteService = expedienteService;
        this.utilizadorService = utilizadorService;
    }

    @GetMapping("/despachos")
    public String listarDespachos(Model model) {
        List<Despacho> despachos = despachoService.listarTodos();
        List<Expediente> expedientes = expedienteService.listarTodos();
        List<Utilizador> utilizadores = utilizadorService.listarTodos();
        model.addAttribute("despachos", despachos);
        model.addAttribute("expedientes", expedientes);
        model.addAttribute("utilizadores", utilizadores);
        return "despachos";
    }

    @GetMapping("/despachos/novo")
    public String novoDespacho(Model model) {
        List<Expediente> expedientes = expedienteService.listarTodos();
        List<Utilizador> utilizadores = utilizadorService.listarTodos();
        model.addAttribute("despacho", new Despacho());
        model.addAttribute("expedientes", expedientes);
        model.addAttribute("utilizadores", utilizadores);
        return "novo-despacho";
    }

    @PostMapping("/despachos/salvar")
    public String salvarDespacho(@ModelAttribute("formDespacho") Despacho despacho) {
        despachoService.guardar(despacho);
        return "redirect:/despachos";
    }
}
