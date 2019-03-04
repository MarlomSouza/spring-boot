package com.brasilprev.loja.dominio;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.brasilprev.loja.dominio.entidade.compras.ItemPedido;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;

public class ItemPedidoTeste {
    private Produto produto;
    private int quantidade;

    @Before
    public void setUp() {
        produto = mock(Produto.class);
        quantidade = 10;
    }

    @Test
    public void deve_criar() {
        BigDecimal subTotal = new BigDecimal(1000);
        BigDecimal valor = new BigDecimal(100);
        when(produto.getPreco()).thenReturn(valor);

        ItemPedido itemPedido = new ItemPedido(produto, quantidade);

        assertEquals(produto, itemPedido.getProduto());
        assertEquals(quantidade, itemPedido.getQuantidade());
        assertEquals(valor, itemPedido.getValor());
        assertEquals(subTotal, itemPedido.getSubTotal());
    }

    @Test
    public void nao_deve_criar_com_produto_invalido() {
        produto = null;

        ThrowingCallable act = () -> {
            new ItemPedido(produto, quantidade);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Produto é inválido");
    }

    @Test
    public void nao_deve_criar_com_quantidade_invalido() {
        quantidade = 0;

        ThrowingCallable act = () -> {
            new ItemPedido(produto, quantidade);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Quantidade é inválida");
    }

}