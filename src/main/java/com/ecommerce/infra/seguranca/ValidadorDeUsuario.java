package com.ecommerce.infra.seguranca;

import static java.util.Collections.emptyList;

import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.infra.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDeUsuario implements UserDetailsService {

    private ClienteRepositorio repositorio;

    @Autowired
    public ValidadorDeUsuario(ClienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Cliente cliente = repositorio.findByNome(nome);

        if (cliente == null)
            throw new UsernameNotFoundException(nome);

        return new User(cliente.getNome(), cliente.getSenha(), emptyList());
    }
}