package mz.co.gestao.gestao_expedientes.repository;

import mz.co.gestao.gestao_expedientes.entity.Utilizador;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    Optional<Utilizador> findByUsername(String username);
}