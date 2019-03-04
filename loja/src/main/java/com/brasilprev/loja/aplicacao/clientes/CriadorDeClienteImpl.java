package com.brasilprev.loja.aplicacao.clientes;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.entidade.clientes.Endereco;
import com.brasilprev.loja.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CriadorDeClienteImpl implements CriadorDeCliente {

    private ClienteRepositorio clienteRepositorio;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CriadorDeClienteImpl(ClienteRepositorio clienteRepositorio, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clienteRepositorio = clienteRepositorio;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Cliente criar(ClienteDto clienteDto) {
        String senhaEncriptada = bCryptPasswordEncoder.encode(clienteDto.senha);

        Endereco endereco = new Endereco(clienteDto.rua, clienteDto.bairro, clienteDto.cep, clienteDto.cidade,
                clienteDto.estado);

        Cliente cliente = new Cliente(clienteDto.nome, clienteDto.email, senhaEncriptada, endereco);
        clienteRepositorio.save(cliente);
        return cliente;
    }
}