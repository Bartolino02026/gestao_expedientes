package mz.co.gestao.gestao_expedientes.service;

import mz.co.gestao.gestao_expedientes.entity.Auditoria;
import mz.co.gestao.gestao_expedientes.repository.AuditoriaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaService {
    private final AuditoriaRepository auditoriaRepository;

    public AuditoriaService(AuditoriaRepository auditoriaRepository) {
        this.auditoriaRepository = auditoriaRepository;
    }

    public List<Auditoria> listarTodos() {
        return auditoriaRepository.findAllByOrderByDataDesc();
    }

    public Auditoria guardar(Auditoria auditoria) {
        if (auditoria.getData() == null) {
            auditoria.setData(LocalDateTime.now());
        }
        return auditoriaRepository.save(auditoria);
    }

    public Auditoria buscarPorId(Long id) {
        return auditoriaRepository.findById(id).orElse(null);
    }
}