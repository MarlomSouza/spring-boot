package com.brasilprev.loja.api.controller;

import java.net.URI;
import java.util.List;

import com.brasilprev.loja.aplicacao.produtos.CriarProduto;
import com.brasilprev.loja.aplicacao.produtos.ProdutoDto;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/produtos")
public class ProdutosController {

    private CriarProduto criarProdutos;
    private ProdutoRepositorio produtoRepositorio;
    private final String apiPath = "api/produtos/";

    @Autowired
    public ProdutosController(ProdutoRepositorio produtoRepositorio, CriarProduto criarProdutos) {
        this.produtoRepositorio = produtoRepositorio;
        this.criarProdutos = criarProdutos;
    }

    @PostMapping
    public ResponseEntity<Produto> post(ProdutoDto produtoDto) {
        Produto produto = criarProdutos.criar(produtoDto);
        URI path = URI.create(apiPath + produto.getId());
        return ResponseEntity.created(path).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Produto>> get() {
        return ResponseEntity.ok(produtoRepositorio.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> get(@PathVariable("id") long id) {
        return ResponseEntity.of(produtoRepositorio.findById(id));
    }
}