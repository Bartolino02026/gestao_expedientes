package mz.co.gestao.gestao_expedientes.controller;

import mz.co.gestao.gestao_expedientes.entity.Utilizador;
import mz.co.gestao.gestao_expedientes.service.UtilizadorService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UtilizadorController {

    private final UtilizadorService utilizadorService;

    public UtilizadorController(UtilizadorService utilizadorService) {
        this.utilizadorService = utilizadorService;
    }

    @GetMapping("/utilizadores")
    public String listarUtilizadores(Model model) {
        List<Utilizador> utilizadores = utilizadorService.listarTodos();

        model.addAttribute("utilizadores", utilizadores);

        return "utilizadores";
    }

    @GetMapping("/utilizadores/novo")
    public String novoUtilizador(Model model) {
        model.addAttribute("utilizador", new Utilizador());

        return "novo-utilizador";
    }

    @PostMapping("/utilizadores/salvar")
    public String salvarUtilizador(@ModelAttribute Utilizador utilizador) {
        utilizadorService.guardar(utilizador);

        return "redirect:/utilizadores";
    }

    @GetMapping("/utilizadores/editar/{id}")
    public String editarUtilizador(@PathVariable Long id, Model model) {
        Utilizador utilizador = utilizadorService.buscarPorId(id);
        model.addAttribute("utilizador", utilizador);
        return "editar-utilizador";
    }

    @PostMapping("/utilizadores/editar")
    public String atualizarUtilizador(@ModelAttribute Utilizador utilizador) {
        utilizadorService.guardar(utilizador);
        return "redirect:/utilizadores";
    }
}