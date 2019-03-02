package com.brasilprev.loja.aplicacao.clientes;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.entidade.clientes.Endereco;

/**
 * MapeadorDeCliente
 */
public class MapeadorDeCliente {

    public static Cliente Mapear(ClienteDto clienteDto) {

        Endereco endereco = new Endereco(clienteDto.rua, clienteDto.bairro, clienteDto.cep, clienteDto.cidade,
                clienteDto.estado);

        return new Cliente(clienteDto.nome, clienteDto.email, clienteDto.senha, endereco);
    }
}