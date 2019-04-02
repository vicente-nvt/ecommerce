package com.ecommerce.infra.repositorio;

import com.ecommerce.dominio.entidades.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {

}