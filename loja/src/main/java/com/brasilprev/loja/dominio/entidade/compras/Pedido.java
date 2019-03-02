package com.brasilprev.loja.dominio.entidade.compras;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.ManyToOne;

import com.brasilprev.loja.dominio.entidade.Entidade;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

public class Pedido extends Entidade {

    private Cliente cliente;
    private StatusPedido statusPedido;
    private ZonedDateTime dataPedido;
    private UUID sessao;
    @ManyToOne
    private List<ItemPedido> itenspedido;

    public Pedido(Cliente cliente) {
        ExcecaoDeDominio.Quando(cliente == null, "Cliente inválido");

        this.cliente = cliente;
        this.statusPedido = StatusPedido.ABERTO;
        this.dataPedido = ZonedDateTime.now();
        this.sessao = UUID.randomUUID();
        this.itenspedido = new ArrayList<ItemPedido>();
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

    public void adicionarItemPedido(ItemPedido itemPedido) {
        ExcecaoDeDominio.Quando(itemPedido == null, "Item do pedido inválido");
        ExcecaoDeDominio.Quando(this.statusPedido == StatusPedido.FECHADO, "Pedido está fechado");

        this.itenspedido.add(itemPedido);
    }

    public List<ItemPedido> getItensPedido() {
        return itenspedido;
    }

}