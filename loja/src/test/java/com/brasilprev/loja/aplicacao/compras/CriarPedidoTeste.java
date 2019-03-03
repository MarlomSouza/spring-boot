package com.brasilprev.loja.aplicacao.compras;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.entidade.compras.ItemPedido;
import com.brasilprev.loja.dominio.entidade.compras.Pedido;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.repositorio.ClienteRepositorio;
import com.brasilprev.loja.repositorio.PedidoRepositorio;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;

public class CriarPedidoTeste {

    private CriarPedidoImpl criarPedido;
    private PedidoRepositorio pedidoRepositorio;
    private PedidoDto pedidoDto;
    private ClienteRepositorio clienteRepositorio;
    private ProdutoRepositorio produtoRepositorio;

    @Before
    public void setUp() {
        pedidoRepositorio = mock(PedidoRepositorio.class);
        clienteRepositorio = mock(ClienteRepositorio.class);
        produtoRepositorio = mock(ProdutoRepositorio.class);
        criarPedido = new CriarPedidoImpl(pedidoRepositorio, clienteRepositorio, produtoRepositorio);
        pedidoDto = new PedidoDto();
        pedidoDto.clienteId = 1;
        pedidoDto.produtoId = 1;
        pedidoDto.quantidade = 3;
    }

    @Test
    public void deve_criar() {
        Cliente cliente = mock(Cliente.class);
        Produto produto = mock(Produto.class);
        when(produto.getPreco()).thenReturn(BigDecimal.TEN);
        when(clienteRepositorio.findById(pedidoDto.clienteId)).thenReturn(Optional.of(cliente));
        when(produtoRepositorio.findById(pedidoDto.produtoId)).thenReturn(Optional.of(produto));

        Pedido pedido = criarPedido.criar(pedidoDto);

        verify(pedidoRepositorio).save(pedido);
    }

    @Test
    public void deve_disparar_exececao_quando_nao_encontrar_cliente() {
        ThrowingCallable act = () -> {
            criarPedido.criar(pedidoDto);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeAplicacao.class)
                .hasMessageContaining("Cliente não foi encontrado");
    }

    @Test
    public void deve_disparar_exececao_quando_nao_encontrar_produto() {
        Cliente cliente = mock(Cliente.class);
        when(clienteRepositorio.findById(pedidoDto.clienteId)).thenReturn(Optional.of(cliente));
        ThrowingCallable act = () -> {
            criarPedido.criar(pedidoDto);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeAplicacao.class)
                .hasMessageContaining("Produto não foi encontrado");
    }

    @Test
    public void deve_disparar_excecao_quando_nao_encontrar_pedido() {
        pedidoDto.pedidoId = 23;
        Cliente cliente = mock(Cliente.class);
        when(clienteRepositorio.findById(pedidoDto.clienteId)).thenReturn(Optional.of(cliente));

        ThrowingCallable act = () -> {
            criarPedido.criar(pedidoDto);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeAplicacao.class)
                .hasMessageContaining("Pedido não foi encontrado");
    }

    @Test
    public void deve_adicionar_um_item_pedido_a_pedido_existente() {
        Cliente cliente = mock(Cliente.class);
        Produto produto = mock(Produto.class);
        Pedido pedido = new Pedido(cliente);
        pedidoDto.pedidoId = 15;
        when(clienteRepositorio.findById(pedidoDto.clienteId)).thenReturn(Optional.of(cliente));
        when(pedidoRepositorio.findById(pedidoDto.pedidoId)).thenReturn(Optional.of(pedido));
        when(produtoRepositorio.findById(pedidoDto.produtoId)).thenReturn(Optional.of(produto));
        when(produto.getPreco()).thenReturn(BigDecimal.TEN);
    
        Pedido pedidoAtualizado = criarPedido.criar(pedidoDto);

        verify(pedidoRepositorio).save(pedidoAtualizado);
    }
}