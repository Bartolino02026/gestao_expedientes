package mz.co.gestao.gestao_expedientes.controller;

import mz.co.gestao.gestao_expedientes.entity.Auditoria;
import mz.co.gestao.gestao_expedientes.entity.Utilizador;
import mz.co.gestao.gestao_expedientes.service.AuditoriaService;
import mz.co.gestao.gestao_expedientes.service.UtilizadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuditoriaController {
    private final AuditoriaService auditoriaService;
    private final UtilizadorService utilizadorService;

    public AuditoriaController(AuditoriaService auditoriaService, UtilizadorService utilizadorService) {
        this.auditoriaService = auditoriaService;
        this.utilizadorService = utilizadorService;
    }

    @GetMapping("/auditoria")
    public String listarAuditoria(Model model) {
        List<Auditoria> auditorias = auditoriaService.listarTodos();
        List<Utilizador> utilizadores = utilizadorService.listarTodos();
        model.addAttribute("auditorias", auditorias);
        model.addAttribute("utilizadores", utilizadores);
        return "auditoria";
    }
}
