package mz.co.gestao.gestao_expedientes.repository;

import mz.co.gestao.gestao_expedientes.entity.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespachoRepository extends JpaRepository<Despacho, Long> {
    List<Despacho> findByExpedienteIdOrderByDataDesc(Long expedienteId);
}
