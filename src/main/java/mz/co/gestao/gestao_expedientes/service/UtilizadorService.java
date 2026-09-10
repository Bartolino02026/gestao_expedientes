package mz.co.gestao.gestao_expedientes.service;

import mz.co.gestao.gestao_expedientes.entity.Utilizador;
import mz.co.gestao.gestao_expedientes.repository.UtilizadorRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;
    private final PasswordEncoder passwordEncoder;


    public UtilizadorService(
            UtilizadorRepository utilizadorRepository,
            PasswordEncoder passwordEncoder) {

        this.utilizadorRepository = utilizadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    public Utilizador buscarPorId(Long id) {
        return utilizadorRepository.findById(id).orElse(null);
    }

    public Utilizador guardar(Utilizador utilizador) {

        String password = utilizador.getPassword();

        if (password != null
                && !password.isBlank()
                && !password.startsWith("$2a$")
                && !password.startsWith("$2b$")
                && !password.startsWith("$2y$")) {

            utilizador.setPassword(passwordEncoder.encode(password));
        }

        return utilizadorRepository.save(utilizador);
    }

    public Utilizador buscarPorUsername(String username) {
        return utilizadorRepository.findByUsername(username).orElse(null);
    }

}