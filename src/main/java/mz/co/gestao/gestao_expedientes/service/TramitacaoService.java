package mz.co.gestao.gestao_expedientes.service;

import mz.co.gestao.gestao_expedientes.entity.Tramitacao;
import mz.co.gestao.gestao_expedientes.repository.TramitacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TramitacaoService {

    private final TramitacaoRepository tramitacaoRepository;

    public TramitacaoService(TramitacaoRepository tramitacaoRepository) {
        this.tramitacaoRepository = tramitacaoRepository;
    }

    public List<Tramitacao> listarTodos() {
        return tramitacaoRepository.findAll();
    }

    public List<Tramitacao> listarPorExpediente(Long expedienteId) {
        return tramitacaoRepository
                .findByExpedienteIdOrderByDataDesc(expedienteId);
    }

    public Tramitacao guardar(Tramitacao tramitacao) {

        if (tramitacao.getData() == null) {
            tramitacao.setData(LocalDateTime.now());
        }

        return tramitacaoRepository.save(tramitacao);
    }

    public Tramitacao buscarPorId(Long id) {
        return tramitacaoRepository.findById(id).orElse(null);

    }

    public void eliminarPorExpediente(Long expedienteId) {
        List<Tramitacao> tramitacoes =
                tramitacaoRepository.findByExpedienteIdOrderByDataDesc(expedienteId);
        tramitacaoRepository.deleteAll(tramitacoes);
    }

}