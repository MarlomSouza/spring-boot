package com.brasilprev.loja.api.controller;

import java.net.URI;
import java.util.List;

import com.brasilprev.loja.aplicacao.produtos.CategoriaDto;
import com.brasilprev.loja.aplicacao.produtos.CriarCategoria;
import com.brasilprev.loja.dominio.entidade.produtos.Categoria;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/categorias")
public class CategoriasController {

    private final String apiPath = "api/categorias/";
    private CriarCategoria criarCategoria;
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    public CategoriasController(CategoriaRepositorio categoriaRepositorio, CriarCategoria criarCategoria) {
        this.categoriaRepositorio = categoriaRepositorio;
        this.criarCategoria = criarCategoria;
    }

    @PostMapping
    public ResponseEntity<?> post(CategoriaDto categoriaDto) {
        Categoria categoria = criarCategoria.criar(categoriaDto);
        URI path = URI.create(apiPath + categoria.getId());
        return ResponseEntity.created(path).build();
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> get() {
        return ResponseEntity.ok(categoriaRepositorio.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> get(@PathVariable long id) {
        return ResponseEntity.of(categoriaRepositorio.findById(id));
    }

}