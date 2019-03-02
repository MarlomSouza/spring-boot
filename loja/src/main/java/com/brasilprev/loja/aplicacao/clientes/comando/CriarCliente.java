package com.brasilprev.loja.aplicacao.clientes.comando;

import com.brasilprev.loja.aplicacao.clientes.ClienteDto;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;

public interface CriarCliente {

    Cliente criar(ClienteDto clienteDto);
}