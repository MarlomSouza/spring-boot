package com.brasilprev.loja.aplicacao.clientes.comando;

import com.brasilprev.loja.aplicacao.clientes.ClienteDto;
import com.brasilprev.loja.aplicacao.clientes.MapeadorDeCliente;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;

public class CriarClienteImpl implements CriarCliente {

    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public CriarClienteImpl(ClienteRepositorio clienteRepositorio) {
        super();
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public Cliente criar(ClienteDto clienteDto) {
        Cliente cliente = MapeadorDeCliente.Mapear(clienteDto);
        clienteRepositorio.save(cliente);
        return cliente;
    }
}