package com.brasilprev.loja.aplicacao.compras;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.dominio.entidade.compras.Pedido;
import com.brasilprev.loja.repositorio.PedidoRepositorio;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;

public class FecharPedidoTeste {

    private PedidoRepositorio pedidoRepositorio;
    private FecharPedido fecharPedido;

    @Before
    public void setUp() {
        pedidoRepositorio = mock(PedidoRepositorio.class);
        fecharPedido = new FecharPedidoImpl(pedidoRepositorio);
    }

    @Test
    public void deve_fechar_um_pedido() {
        final long pedidoId = 5;
        Pedido pedido = mock(Pedido.class);
        when(pedidoRepositorio.findById(pedidoId)).thenReturn(Optional.of(pedido));

        fecharPedido.executar(pedidoId);

        verify(pedidoRepositorio).save(pedido);
    }

    @Test
    public void deve_disparar_excecao_quando_nao_encontrar_pedido() {
        final long pedidoId = 5;
        ThrowingCallable act = () -> {
            fecharPedido.executar(pedidoId);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeAplicacao.class).hasMessageContaining("Pedido não encontrado");
    }
}