package mz.co.gestao.gestao_expedientes.service;

import mz.co.gestao.gestao_expedientes.entity.Utilizador;
import mz.co.gestao.gestao_expedientes.repository.UtilizadorRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilizadorRepository utilizadorRepository;

    public CustomUserDetailsService(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Utilizador utilizador = utilizadorRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Utilizador não encontrado: " + username
                        ));

        return User.builder()
                .username(utilizador.getUsername())
                .password(utilizador.getPassword())
                .roles(utilizador.getPapel())
                .disabled(!utilizador.isActivo())
                .build();
    }
}