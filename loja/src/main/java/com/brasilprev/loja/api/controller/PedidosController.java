package com.brasilprev.loja.api.controller;

import java.net.URI;
import java.util.List;

import com.brasilprev.loja.aplicacao.compras.CriadorDePedido;
import com.brasilprev.loja.aplicacao.compras.PedidoDto;
import com.brasilprev.loja.dominio.entidade.compras.Pedido;
import com.brasilprev.loja.repositorio.PedidoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PedidosController.API_PATH)
public class PedidosController {

    static final String API_PATH = "api/pedidos/";
    private PedidoRepositorio pedidoRepositorio;
    private CriadorDePedido criadorDePedido;

    @Autowired
    public PedidosController(PedidoRepositorio pedidoRepositorio, CriadorDePedido criadorDePedido) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.criadorDePedido = criadorDePedido;
    }

    @PostMapping
    public ResponseEntity<Pedido> post(@RequestBody PedidoDto pedidoDto) {
        Pedido pedido = criadorDePedido.criar(pedidoDto);
        URI uri = URI.create(API_PATH + pedido.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> get() {
        return ResponseEntity.ok(pedidoRepositorio.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Pedido> get(@PathVariable long id) {
        return ResponseEntity.of(pedidoRepositorio.findById(id));
    }

    @PutMapping(PedidosController.API_PATH + "{id}/adicionarItem")
    public ResponseEntity<Pedido> put(@PathVariable long id, @RequestBody PedidoDto pedidoDto) {
        pedidoDto.pedidoId = id;
        criadorDePedido.criar(pedidoDto);
        return ResponseEntity.noContent().build();
    }
}