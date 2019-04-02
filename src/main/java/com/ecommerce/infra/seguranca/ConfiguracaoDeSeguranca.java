package com.ecommerce.infra.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    private ValidadorDeUsuario validador;
    private CodificacaoDeSenha codificadorDeSenha;
    private static final String[] ListaDeExclusao = { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
            "/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**" };

    @Autowired
    public ConfiguracaoDeSeguranca(ValidadorDeUsuario validador, CodificacaoDeSenha codificadorDeSenha) {
        this.validador = validador;
        this.codificadorDeSenha = codificadorDeSenha;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests().antMatchers(ListaDeExclusao).permitAll()
                .antMatchers(HttpMethod.POST, "/rest/login/").permitAll().anyRequest().authenticated().and()
                .addFilterBefore(new FiltroDeLogin("/rest/login/", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new FiltroDeAutenticacao(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(validador).passwordEncoder(codificadorDeSenha);
    }
}