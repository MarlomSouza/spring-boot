package com.brasilprev.loja.aplicacao.clientes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.repositorio.ClienteRepositorio;

import org.junit.Test;

public class CriadorDeClienteTeste {

    @Test
    public void deve_criar() {
        ClienteRepositorio repositorio = mock(ClienteRepositorio.class);
        CriadorDeCliente criadorDeCliente = new CriadorDeClienteImpl(repositorio);
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.nome = "Marlom";
        clienteDto.email = "marlom@hotmai.com";
        clienteDto.senha = "senha123";
        clienteDto.rua = "rua";
        clienteDto.bairro = "bairro";
        clienteDto.cep = "79112202";
        clienteDto.cidade = "Campo grande";
        clienteDto.estado = "ms";

        Cliente cliente = criadorDeCliente.criar(clienteDto);

        verify(repositorio).save(cliente);
    }
}