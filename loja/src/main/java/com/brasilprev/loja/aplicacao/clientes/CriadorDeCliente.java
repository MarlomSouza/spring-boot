package com.brasilprev.loja.aplicacao.clientes
;

import com.brasilprev.loja.aplicacao.clientes.ClienteDto;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;

public interface CriadorDeCliente {
    Cliente criar(ClienteDto clienteDto);
}