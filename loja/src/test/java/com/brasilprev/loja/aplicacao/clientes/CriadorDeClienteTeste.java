package com.brasilprev.loja.aplicacao.clientes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.repositorio.ClienteRepositorio;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriadorDeClienteTeste {

    @Test
    public void deve_criar() {
        BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);
        ClienteRepositorio repositorio = mock(ClienteRepositorio.class);
        CriadorDeCliente criadorDeCliente = new CriadorDeClienteImpl(repositorio, encoder);
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.nome = "Marlom";
        clienteDto.email = "marlom@hotmai.com";
        clienteDto.senha = "senha123";
        clienteDto.rua = "rua";
        clienteDto.bairro = "bairro";
        clienteDto.cep = "79112202";
        clienteDto.cidade = "Campo grande";
        clienteDto.estado = "ms";
        when(encoder.encode(clienteDto.senha)).thenReturn("sdasd8234");

        Cliente cliente = criadorDeCliente.executar(clienteDto);

        verify(repositorio).save(cliente);
    }
}