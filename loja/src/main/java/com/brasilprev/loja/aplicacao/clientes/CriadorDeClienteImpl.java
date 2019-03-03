package com.brasilprev.loja.aplicacao.clientes;

import com.brasilprev.loja.aplicacao.clientes.ClienteDto;
import com.brasilprev.loja.aplicacao.clientes.MapeadorDeCliente;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriadorDeClienteImpl implements CriadorDeCliente {

    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public CriadorDeClienteImpl(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public Cliente criar(ClienteDto clienteDto) {
        Cliente cliente = MapeadorDeCliente.Mapear(clienteDto);
        clienteRepositorio.save(cliente);
        return cliente;
    }
}