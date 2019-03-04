package com.brasilprev.loja.dominio;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.entidade.compras.ItemPedido;
import com.brasilprev.loja.dominio.entidade.compras.Pedido;
import com.brasilprev.loja.dominio.entidade.compras.StatusPedido;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;

public class PedidoTeste {

    private Cliente cliente;

    @Before
    public void setUp() {
        cliente = mock(Cliente.class);
    }

    @Test
    public void deve_criar() {
        StatusPedido statusEsperado = StatusPedido.ABERTO;

        Pedido pedido = new Pedido(cliente);

        assertEquals(cliente, pedido.getCliente());
        assertEquals(statusEsperado, pedido.getStatusPedido());
        assertNotNull(pedido.getSessao());
    }

    @Test
    public void nao_deve_criar_com_cliente_invalido() {
        ThrowingCallable act = () -> {
            new Pedido(null);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Cliente inválido");
    }

    @Test
    public void deve_alterar_status_do_pedido() {
        StatusPedido statusEsperado = StatusPedido.FECHADO;
        Pedido pedido = new Pedido(cliente);

        pedido.fecharPedido();

        assertEquals(statusEsperado, pedido.getStatusPedido());
    }

    @Test
    public void deve_adicionar_um_item_pedido() {
        Pedido pedido = new Pedido(cliente);
        Produto produto = mock(Produto.class);
        when(produto.getPreco()).thenReturn(new BigDecimal(10));
        ItemPedido itemPedido = new ItemPedido(pedido, produto, 10);
        ItemPedido[] itensPedidoEsperado = { itemPedido };

        pedido.adicionarItemPedido(itemPedido);

        assertArrayEquals(itensPedidoEsperado, pedido.getItensPedido().toArray());
    }

    @Test
    public void nao_deve_adicionar_um_item_pedido_invalido() {
        Pedido pedido = new Pedido(cliente);

        ThrowingCallable act = () -> {
            pedido.adicionarItemPedido(null);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Item do pedido inválido");
    }

    @Test
    public void nao_deve_adicionar_um_item_pedido_quando_pedido_estiver_fechado() {
        Pedido pedido = new Pedido(cliente);
        pedido.fecharPedido();

        ThrowingCallable act = () -> {
            pedido.adicionarItemPedido(mock(ItemPedido.class));
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Pedido está fechado");
    }
}