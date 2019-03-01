package com.brasilprev.loja;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.entidade.compras.Pedido;
import com.brasilprev.loja.dominio.entidade.compras.StatusPedido;
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
        LocalDate dataEsperada = ZonedDateTime.now().toLocalDate();
        StatusPedido statusEsperado = StatusPedido.ABERTO;

        Pedido pedido = new Pedido(cliente);

        assertEquals(cliente, pedido.getCliente());
        assertEquals(dataEsperada, pedido.getDataPedido().toLocalDate());
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

}