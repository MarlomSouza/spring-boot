package com.brasilprev.loja.dominio.entidade.compras;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Cliente cliente;
    private StatusPedido statusPedido;
    private ZonedDateTime dataPedido;
    private UUID sessao;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemPedido> itenspedido;

    public Pedido(Cliente cliente) {
        ExcecaoDeDominio.Quando(cliente == null, "Cliente inválido");

        this.cliente = cliente;
        this.statusPedido = StatusPedido.ABERTO;
        this.dataPedido = ZonedDateTime.now();
        this.sessao = UUID.randomUUID();
        this.itenspedido = new ArrayList<ItemPedido>();
    }

    public long getId() {
        return id;
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