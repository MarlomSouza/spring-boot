package com.brasilprev.loja.api.controller;

import java.net.URI;
import java.util.List;

import com.brasilprev.loja.aplicacao.compras.CriarPedido;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PedidosController.API_PEDIDOS)
public class PedidosController {

    static final String API_PEDIDOS = "api/pedidos/";

    private PedidoRepositorio pedidoRepositorio;
    private CriarPedido criarPedido;

    @Autowired
    public PedidosController(PedidoRepositorio pedidoRepositorio, CriarPedido criarPedido) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.criarPedido = criarPedido;
    }

    @PostMapping
    public ResponseEntity<Pedido> post(PedidoDto pedidoDto) {
        Pedido pedido = criarPedido.criar(pedidoDto);
        URI uri = URI.create(API_PEDIDOS + pedido.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> get() {
        return ResponseEntity.ok(pedidoRepositorio.findAll());
    }

    @GetMapping
    public ResponseEntity<Pedido> get(@PathVariable long id) {
        return ResponseEntity.of(pedidoRepositorio.findById(id));
    }

    @PutMapping(PedidosController.API_PEDIDOS + "{id}/adicionarItem")
    public ResponseEntity<Pedido> put(@PathVariable long id, PedidoDto pedidoDto) {
        pedidoDto.pedidoId = id;
        criarPedido.criar(pedidoDto);
        return ResponseEntity.noContent().build();
    }
}