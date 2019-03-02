package com.brasilprev.loja.repositorio;

import com.brasilprev.loja.dominio.entidade.compras.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepositorio extends JpaRepository<Pedido, Long> {
}