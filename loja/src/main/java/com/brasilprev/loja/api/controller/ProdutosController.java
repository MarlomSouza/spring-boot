package com.brasilprev.loja.api.controller;

import java.net.URI;
import java.util.List;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.aplicacao.produtos.CriadorDeProduto;
import com.brasilprev.loja.aplicacao.produtos.ProdutoDto;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ProdutosController.API_PATH)
public class ProdutosController {

    static final String API_PATH = "api/produtos";
    private final CriadorDeProduto criadorDeProduto;
    private final ProdutoRepositorio produtoRepositorio;

    @Autowired
    public ProdutosController(ProdutoRepositorio produtoRepositorio, CriadorDeProduto criadorDeProduto) {
        this.produtoRepositorio = produtoRepositorio;
        this.criadorDeProduto = criadorDeProduto;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ProdutoDto produtoDto) {
        try {
            Produto produto = criadorDeProduto.executar(produtoDto);
            URI path = URI.create(API_PATH + "/" + produto.getId());
            return ResponseEntity.created(path).build();
        } catch (ExcecaoDeAplicacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<Produto>> get() {
        return ResponseEntity.ok(produtoRepositorio.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> get(@PathVariable("id") long id) {
        return ResponseEntity.of(produtoRepositorio.findById(id));
    }
}