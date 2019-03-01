package com.loja;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.loja.entidade.Cliente;
import com.loja.entidade.Pedido;
import com.loja.entidade.StatusPedido;
import com.loja.excecao.ExcecaoDeDominio;

import org.junit.Before;
import org.junit.Test;

public class PedidoTeste {

    private ZonedDateTime data;
    private Cliente cliente;

    @Before
    public void SetUp() {
        cliente = mock(Cliente.class);

    }

    @Test
    public void deve_criar() {
        Pedido pedido = new Pedido(cliente);
        LocalDate dataEsperada = ZonedDateTime.now().toLocalDate();
        StatusPedido statusEsperado = StatusPedido.ABERTO;

        assertEquals(cliente, pedido.getCliente());
        assertEquals(dataEsperada, pedido.getDataPedido().toLocalDate());
        assertEquals(statusEsperado, pedido.getStatusPedido());
        assertNotNull(pedido.getSessao());
    }

    @Test
    public void nao_deve_criar_com_cliente_invalido() {
        assertThatThrownBy(() -> {
            new Pedido(null);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Cliente inválido");
    }

    @Test
    public void deve_alterar_status_do_pedido() {
        StatusPedido statusEsperado = StatusPedido.FECHADO;
        Pedido pedido = new Pedido(cliente);

        pedido.fecharPedido();

        assertEquals(statusEsperado, pedido.getStatusPedido());
    }

}