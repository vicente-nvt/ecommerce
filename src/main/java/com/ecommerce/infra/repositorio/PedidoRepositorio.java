package com.ecommerce.infra.repositorio;

import com.ecommerce.dominio.entidades.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{
    Pedido findById(long id);
}