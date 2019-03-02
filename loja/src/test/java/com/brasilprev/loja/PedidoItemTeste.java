package com.brasilprev.loja;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.brasilprev.loja.dominio.entidade.compras.Pedido;
import com.brasilprev.loja.dominio.entidade.compras.PedidoItem;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;

public class PedidoItemTeste {

    private Pedido pedido;
    private Produto produto;
    private int quantidade;

    @Before
    public void setUp() {
        pedido = mock(Pedido.class);
        produto = mock(Produto.class);
        quantidade = 10;
    }

    @Test
    public void deve_criar() {
        BigDecimal subTotal = new BigDecimal(1000);
        BigDecimal valor = new BigDecimal(100);
        when(produto.getPreco()).thenReturn(valor);

        PedidoItem pedidoItem = new PedidoItem(pedido, produto, quantidade);

        assertEquals(pedido, pedidoItem.getPedido());
        assertEquals(produto, pedidoItem.getProduto());
        assertEquals(quantidade, pedidoItem.getQuantidade());
        assertEquals(valor, pedidoItem.getValor());
        assertEquals(subTotal, pedidoItem.getSubTotal());
    }

    @Test
    public void nao_deve_criar_com_pedido_invalido() {
        pedido = null;

        ThrowingCallable act = () -> {
            new PedidoItem(pedido, produto, quantidade);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Pedido é inválido");
    }

    @Test
    public void nao_deve_criar_com_produto_invalido() {
        produto = null;

        ThrowingCallable act = () -> {
            new PedidoItem(pedido, produto, quantidade);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Produto é inválido");
    }

    @Test
    public void nao_deve_criar_com_quantidade_invalido() {
        quantidade = 0;

        ThrowingCallable act = () -> {
            new PedidoItem(pedido, produto, quantidade);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Quantidade é inválida");
    }

}