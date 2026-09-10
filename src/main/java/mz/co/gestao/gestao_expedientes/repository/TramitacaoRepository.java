package mz.co.gestao.gestao_expedientes.repository;

import mz.co.gestao.gestao_expedientes.entity.Tramitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TramitacaoRepository extends JpaRepository<Tramitacao, Long> {

    List<Tramitacao> findByExpedienteIdOrderByDataDesc(Long expedienteId);
}