package mz.co.gestao.gestao_expedientes.service;

import mz.co.gestao.gestao_expedientes.entity.Expediente;
import mz.co.gestao.gestao_expedientes.repository.ExpedienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpedienteService {

    private final ExpedienteRepository expedienteRepository;

    public ExpedienteService(ExpedienteRepository expedienteRepository) {
        this.expedienteRepository = expedienteRepository;
    }

    public List<Expediente> listarTodos() {
        return expedienteRepository.findAll();
    }

    public Expediente guardar(Expediente expediente) {
        return expedienteRepository.save(expediente);
    }

    public Expediente buscarPorId(Long id) {
        return expedienteRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        expedienteRepository.deleteById(id);
    }
}