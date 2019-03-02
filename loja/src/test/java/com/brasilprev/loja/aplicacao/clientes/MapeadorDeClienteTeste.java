package com.brasilprev.loja.aplicacao.clientes;

import static org.junit.Assert.assertEquals;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.entidade.clientes.Endereco;

import org.junit.Before;
import org.junit.Test;

/**
 * MapeadorDeClienteTeste
 */
public class MapeadorDeClienteTeste {

    private String nome;
    private String email;
    private String senha;
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    @Before
    public void setUp() {
        nome = "Marlom";
        email = "marlom@hotmai.com";
        senha = "senha123";
        rua = "rua";
        bairro = "bairro";
        cep = "79112202";
        cidade = "Campo grande";
        estado = "ms";
    }

    @Test
    public void deve_mapear() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.nome = nome;
        clienteDto.email = email;
        clienteDto.senha = senha;
        clienteDto.rua = rua;
        clienteDto.bairro = bairro;
        clienteDto.cep = cep;
        clienteDto.cidade = cidade;
        clienteDto.estado = estado;

        Cliente cliente = MapeadorDeCliente.Mapear(clienteDto);
        Endereco endereco = cliente.getEndereco();

        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
        assertEquals(senha, cliente.getSenha());
        assertEquals(rua, endereco.getRua());
        assertEquals(bairro, endereco.getBairro());
        assertEquals(cep, endereco.getCep());
        assertEquals(cidade, endereco.getCidade());
        assertEquals(estado, endereco.getEstado());
    }
}