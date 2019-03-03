package com.brasilprev.loja.api.controller;

import java.net.URI;
import java.util.List;

import com.brasilprev.loja.aplicacao.clientes.ClienteDto;
import com.brasilprev.loja.aplicacao.clientes.CriadorDeCliente;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ClientesController.API_PATH)
public class ClientesController {

    static final String API_PATH = "api/clientes/";
    private ClienteRepositorio clienteRepositorio;
    private CriadorDeCliente criadorDeCliente;

    @Autowired
    public ClientesController(ClienteRepositorio clienteRepositorio, CriadorDeCliente criadorDeCliente) {
        this.clienteRepositorio = clienteRepositorio;
        this.criadorDeCliente = criadorDeCliente;
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> get() {
        return ResponseEntity.ok(clienteRepositorio.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> get(@PathVariable("id") long id) {
        return ResponseEntity.of(clienteRepositorio.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> post(ClienteDto clienteDto) {
        Cliente cliente = criadorDeCliente.criar(clienteDto);
        URI path = URI.create(API_PATH + cliente.getId());
        return ResponseEntity.created(path).build();
    }
}