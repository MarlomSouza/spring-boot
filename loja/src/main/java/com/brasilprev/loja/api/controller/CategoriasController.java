package com.brasilprev.loja.api.controller;

import java.net.URI;
import java.util.List;

import com.brasilprev.loja.aplicacao.produtos.CategoriaDto;
import com.brasilprev.loja.aplicacao.produtos.CriadorDeCategoria;
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
@RequestMapping(CategoriasController.API_CATEGORIAS)
public class CategoriasController {

    static final String API_CATEGORIAS = "api/categorias/";
    private CriadorDeCategoria criadorDeCategoria;
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    public CategoriasController(CategoriaRepositorio categoriaRepositorio, CriadorDeCategoria criadorDeCategoria) {
        this.categoriaRepositorio = categoriaRepositorio;
        this.criadorDeCategoria = criadorDeCategoria;
    }

    @PostMapping
    public ResponseEntity<?> post(CategoriaDto categoriaDto) {
        Categoria categoria = criadorDeCategoria.criar(categoriaDto);
        URI path = URI.create(API_CATEGORIAS + categoria.getId());
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