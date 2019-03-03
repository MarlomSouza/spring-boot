package com.brasilprev.loja.aplicacao.compras;

import com.brasilprev.loja.dominio.entidade.compras.Pedido;

public interface CriadorDePedido {
    Pedido criar(PedidoDto pedidoDto);
}