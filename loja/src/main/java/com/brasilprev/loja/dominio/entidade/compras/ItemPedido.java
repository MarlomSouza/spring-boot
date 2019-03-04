package com.brasilprev.loja.dominio.entidade.compras;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Produto produto;
    private int quantidade;
    private BigDecimal valor;
    private BigDecimal subTotal;

    private ItemPedido() {
    }

    public ItemPedido(Produto produto, int quantidade) {
        validar(produto, quantidade);

        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = produto.getPreco();
        this.subTotal = valor.multiply(new BigDecimal(quantidade));
    }

    private void validar( Produto produto, int quantidade) {
        ExcecaoDeDominio.Quando(produto == null, "Produto é inválido");
        ExcecaoDeDominio.Quando(quantidade <= 0, "Quantidade é inválida");
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