package mz.co.gestao.gestao_expedientes.repository;

import mz.co.gestao.gestao_expedientes.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
    List<Auditoria> findAllByOrderByDataDesc();
}