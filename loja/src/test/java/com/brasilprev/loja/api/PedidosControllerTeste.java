
package com.brasilprev.loja.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.brasilprev.loja.api.controller.PedidosController;
import com.brasilprev.loja.aplicacao.compras.CriadorDePedido;
import com.brasilprev.loja.aplicacao.compras.PedidoDto;
import com.brasilprev.loja.dominio.entidade.compras.Pedido;
import com.brasilprev.loja.repositorio.PedidoRepositorio;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PedidosControllerTeste {

    @Spy
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    private PedidoRepositorio pedidoRepositorio;
    private CriadorDePedido criadorDePedido;
    private PedidosController pedidosController;
    private Pedido pedido;

    @Before
    public void setUp() {
        pedidoRepositorio = mock(PedidoRepositorio.class);
        criadorDePedido = mock(CriadorDePedido.class);
        pedidosController = new PedidosController(pedidoRepositorio, criadorDePedido);
        pedido = mock(Pedido.class);
    }

    @Test
    public void deve_criar() {
        final long pedidoId = 3;
        final String localizacaoCriado = "api/pedidos/" + pedidoId;

        PedidoDto pedidoDto = mock(PedidoDto.class);
        when(criadorDePedido.criar(pedidoDto)).thenReturn(pedido);
        when(pedido.getId()).thenReturn(pedidoId);

        ResponseEntity<Pedido> response = pedidosController.post(pedidoDto);

        assertEquals(localizacaoCriado, response.getHeaders().getLocation().getPath());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void deve_obter_todos() {
        when(pedidoRepositorio.findAll()).thenReturn(pedidos);

        ResponseEntity<List<Pedido>> response = pedidosController.get();

        assertEquals(pedidos, response.getBody());
    }

    @Test
    public void deve_obter_por_id() {
        final long pedidoId = 79;
        Pedido pedido = mock(Pedido.class);
        when(pedidoRepositorio.findById(pedidoId)).thenReturn(Optional.of(pedido));

        ResponseEntity<Pedido> response = pedidosController.get(pedidoId);

        assertEquals(pedido, response.getBody());
    }

    @Test
    public void deve_retornar_nao_encontrado() {
        final long pedidoId = 2;

        ResponseEntity<Pedido> response = pedidosController.get(pedidoId);

        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deve_adicionar_item_pedido_a_um_pedido() {
        final long pedidoId = 2;
        final PedidoDto pedidoDto = mock(PedidoDto.class);
        when(criadorDePedido.criar(pedidoDto)).thenReturn(pedido);

        ResponseEntity<Pedido> response = pedidosController.put(pedidoId, pedidoDto);

        verify(criadorDePedido).criar(pedidoDto);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}