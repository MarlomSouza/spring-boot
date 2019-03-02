package com.brasilprev.loja.aplicacao.clientes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.brasilprev.loja.aplicacao.clientes.comando.CriarCliente;
import com.brasilprev.loja.aplicacao.clientes.comando.CriarClienteImpl;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.repositorio.ClienteRepositorio;

import org.junit.Test;

public class CriarClienteTeste {

    @Test
    public void deve_criar() {
        ClienteRepositorio repositorio = mock(ClienteRepositorio.class);
        CriarCliente criarCliente = new CriarClienteImpl(repositorio);
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.nome = "Marlom";
        clienteDto.email = "marlom@hotmai.com";
        clienteDto.senha = "senha123";
        clienteDto.rua = "rua";
        clienteDto.bairro = "bairro";
        clienteDto.cep = "79112202";
        clienteDto.cidade = "Campo grande";
        clienteDto.estado = "ms";

        Cliente cliente = criarCliente.criar(clienteDto);

        verify(repositorio).save(cliente);
    }
}