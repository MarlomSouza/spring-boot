package com.brasilprev.loja.dominio.entidade.compras;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.brasilprev.loja.dominio.entidade.Entidade;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

public class Pedido extends Entidade {

    private Cliente cliente;
    private StatusPedido statusPedido;
    private ZonedDateTime dataPedido;
    private UUID sessao;

    public Pedido(Cliente cliente) {
        ExcecaoDeDominio.Quando(cliente == null, "Cliente inválido");

        this.cliente = cliente;
        this.statusPedido = StatusPedido.ABERTO;
        this.dataPedido = ZonedDateTime.now();
        this.sessao = UUID.randomUUID();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ZonedDateTime getDataPedido() {
        return dataPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public UUID getSessao() {
        return sessao;
    }

    public void fecharPedido() {
        this.statusPedido = StatusPedido.FECHADO;
    }

}