package com.ecommerce.infra.repositorio;

import com.ecommerce.dominio.entidades.ItemDoPedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDoPedidoRepositorio extends JpaRepository<ItemDoPedido, Long> {

}