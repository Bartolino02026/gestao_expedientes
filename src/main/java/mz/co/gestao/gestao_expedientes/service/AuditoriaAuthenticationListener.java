package mz.co.gestao.gestao_expedientes.service;

import mz.co.gestao.gestao_expedientes.entity.Auditoria;
import mz.co.gestao.gestao_expedientes.entity.Utilizador;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuditoriaAuthenticationListener {
    private final AuditoriaService auditoriaService;
    private final UtilizadorService utilizadorService;

    public AuditoriaAuthenticationListener(AuditoriaService auditoriaService, UtilizadorService utilizadorService) {
        this.auditoriaService = auditoriaService;
        this.utilizadorService = utilizadorService;
    }

    @EventListener
    public void registarLogin(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        Utilizador utilizador = utilizadorService.buscarPorUsername(username);
        if (utilizador == null) {
            return;
        }
        Auditoria auditoria = new Auditoria();
        auditoria.setUtilizadorId(utilizador.getId());
        auditoria.setAccao("LOGIN");
        auditoria.setDescricao("Utilizador '" + username + "' iniciou sessão no sistema.");
        auditoriaService.guardar(auditoria);
    }
}