package mz.co.gestao.gestao_expedientes.repository;

import mz.co.gestao.gestao_expedientes.entity.Expediente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {
}