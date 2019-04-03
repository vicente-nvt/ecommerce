package com.ecommerce.aplicacao.cliente;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.entidades.Cliente;

public class ClienteDto implements ObjetoDto<Cliente>{

    private String nome;
    private String email;
    private String senha;
    private EnderecoDto endereco;
    private long id;
    
    public ClienteDto(long id, String nome, String email, String senha, EnderecoDto endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

	public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public void setEndereco(EnderecoDto endereco){
        this.endereco = endereco;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public String getSenha(){
        return this.senha;
    }

    public EnderecoDto getEndereco(){
        return this.endereco;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public boolean equals(ClienteDto outroDto) {
        return this.id == outroDto.getId()
            && this.nome.equals(outroDto.getNome())
            && this.email.equals(outroDto.getEmail())
            && this.senha.equals(outroDto.getSenha())
            && this.endereco.equals(outroDto.getEndereco());
    }
}
