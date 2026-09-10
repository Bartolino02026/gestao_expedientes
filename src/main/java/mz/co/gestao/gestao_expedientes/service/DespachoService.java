package mz.co.gestao.gestao_expedientes.service;

import mz.co.gestao.gestao_expedientes.entity.Despacho;
import mz.co.gestao.gestao_expedientes.repository.DespachoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DespachoService {
    private final DespachoRepository despachoRepository;

    public DespachoService(DespachoRepository despachoRepository) {
        this.despachoRepository = despachoRepository;
    }

    public List<Despacho> listarTodos() {
        return despachoRepository.findAll();
    }

    public List<Despacho> listarPorExpediente(Long expedienteId) {
        return despachoRepository.findByExpedienteIdOrderByDataDesc(expedienteId);
    }

    public Despacho guardar(Despacho despacho) {
        if (despacho.getData() == null) {
            despacho.setData(LocalDateTime.now());
        }
        return despachoRepository.save(despacho);
    }

    public Despacho buscarPorId(Long id) {
        return despachoRepository.findById(id).orElse(null);
    }

    public void eliminarPorExpediente(Long expedienteId) {
        List<Despacho> despachos =
                despachoRepository.findByExpedienteIdOrderByDataDesc(expedienteId);
        despachoRepository.deleteAll(despachos);
    }

}
