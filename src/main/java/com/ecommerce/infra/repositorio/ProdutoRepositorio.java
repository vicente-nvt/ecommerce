package com.ecommerce.infra.repositorio;

import com.ecommerce.dominio.entidades.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
    Produto findById(long id);
}