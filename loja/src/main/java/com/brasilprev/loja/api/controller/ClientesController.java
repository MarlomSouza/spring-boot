package com.brasilprev.loja.api.controller;

import java.net.URI;
import java.util.List;

import com.brasilprev.loja.aplicacao.clientes.ClienteDto;
import com.brasilprev.loja.aplicacao.clientes.comando.CriarCliente;
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
@RequestMapping("api/clientes")
public class ClientesController {

    private ClienteRepositorio clienteRepositorio;
    private CriarCliente criaCliente;
    private final String apiPath = "api/clientes/";

    @Autowired
    public ClientesController(ClienteRepositorio clienteRepositorio, CriarCliente criarCliente) {
        this.clienteRepositorio = clienteRepositorio;
        this.criaCliente = criarCliente;
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> get() {
        return ResponseEntity.ok(clienteRepositorio.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> get(@PathVariable("id") long id) {
        return ResponseEntity.of(clienteRepositorio.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> post(ClienteDto clienteDto) {
        Cliente cliente = criaCliente.criar(clienteDto);
        URI path = URI.create(apiPath + cliente.getId());
        return ResponseEntity.created(path).build();
    }
}