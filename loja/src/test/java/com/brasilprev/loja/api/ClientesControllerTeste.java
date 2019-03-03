package com.brasilprev.loja.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.brasilprev.loja.api.controller.ClientesController;
import com.brasilprev.loja.aplicacao.clientes.ClienteDto;
import com.brasilprev.loja.aplicacao.clientes.CriadorDeCliente;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.repositorio.ClienteRepositorio;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ClientesControllerTeste {

    @Spy
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private ClientesController clientesController;
    private ClienteRepositorio clienteRepositorio;
    private CriadorDeCliente criadorDeCliente;
    private Cliente cliente;

    @Before
    public void setUp() {
        clienteRepositorio = mock(ClienteRepositorio.class);
        criadorDeCliente = mock(CriadorDeCliente.class);
        clientesController = new ClientesController(clienteRepositorio, criadorDeCliente);
        cliente = mock(Cliente.class);
    }

    @Test
    public void deve_obter_todos() {
        when(clienteRepositorio.findAll()).thenReturn(clientes);

        ResponseEntity<List<Cliente>> response = clientesController.get();

        assertEquals(clientes, response.getBody());
    }

    @Test
    public void deve_obter_por_id() {
        final long clienteId = 4;
        when(clienteRepositorio.findById(clienteId)).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = clientesController.get(clienteId);

        assertEquals(cliente, response.getBody());
    }

    @Test
    public void deve_criar() {
        final long clienteId = 4;
        final String localizacaoClienteCriado = "api/clientes/" + clienteId;
        ClienteDto clienteDto = mock(ClienteDto.class);
        when(cliente.getId()).thenReturn(clienteId);
        when(criadorDeCliente.criar(clienteDto)).thenReturn(cliente);

        ResponseEntity<Cliente> response = clientesController.post(clienteDto);

        verify(criadorDeCliente).criar(clienteDto);
        assertEquals(localizacaoClienteCriado, response.getHeaders().getLocation().getPath());
    }

    @Test
    public void deve_retornar_nao_encontrado() {
        final long clienteId = 0;

        ResponseEntity<Cliente> response = clientesController.get(clienteId);

        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}