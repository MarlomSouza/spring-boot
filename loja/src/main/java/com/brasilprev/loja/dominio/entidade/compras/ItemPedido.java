package com.brasilprev.loja.dominio.entidade.compras;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Pedido pedido;
    @OneToOne
    private Produto produto;
    private int quantidade;
    private BigDecimal valor;
    private BigDecimal subTotal;

    public ItemPedido(Pedido pedido, Produto produto, int quantidade) {
        validar(pedido, produto, quantidade);

        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = produto.getPreco();
        this.subTotal = valor.multiply(new BigDecimal(quantidade));
    }

    private void validar(Pedido pedido, Produto produto, int quantidade) {
        ExcecaoDeDominio.Quando(pedido == null, "Pedido é inválido");
        ExcecaoDeDominio.Quando(produto == null, "Produto é inválido");
        ExcecaoDeDominio.Quando(quantidade <= 0, "Quantidade é inválida");
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

}