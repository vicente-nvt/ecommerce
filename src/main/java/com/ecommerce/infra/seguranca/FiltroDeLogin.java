package com.ecommerce.infra.seguranca;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FiltroDeLogin extends AbstractAuthenticationProcessingFilter {

    protected FiltroDeLogin(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        Credenciais credenciais = new ObjectMapper().readValue(request.getInputStream(), Credenciais.class);

        Authentication autenticacao = getAuthenticationManager()
            .authenticate(new UsernamePasswordAuthenticationToken(
                credenciais.getUsuario(), credenciais.getSenha(), Collections.emptyList()));

        return autenticacao;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain, Authentication auth) throws IOException, ServletException {

        AutenticadorDeToken.addAuthentication(response, auth.getName());
    }
}