package com.brasilprev.loja.api.controller;

import java.net.URI;
import java.util.List;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.aplicacao.produtos.CategoriaDto;
import com.brasilprev.loja.aplicacao.produtos.CriadorDeCategoria;
import com.brasilprev.loja.aplicacao.produtos.ExcluirCategoria;
import com.brasilprev.loja.dominio.entidade.produtos.Categoria;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CategoriasController.API_CATEGORIAS)
public class CategoriasController {

    static final String API_CATEGORIAS = "api/categorias";
    private final CriadorDeCategoria criadorDeCategoria;
    private final CategoriaRepositorio categoriaRepositorio;
    private final ExcluirCategoria excluirCategoria;

    @Autowired
    public CategoriasController(CategoriaRepositorio categoriaRepositorio, CriadorDeCategoria criadorDeCategoria,
            ExcluirCategoria excluirCategoria) {
        this.categoriaRepositorio = categoriaRepositorio;
        this.criadorDeCategoria = criadorDeCategoria;
        this.excluirCategoria = excluirCategoria;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody CategoriaDto categoriaDto) {
        try {
            Categoria categoria = criadorDeCategoria.executar(categoriaDto);
            URI path = URI.create(API_CATEGORIAS + "/" + categoria.getId());
            return ResponseEntity.created(path).build();
        } catch (ExcecaoDeAplicacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> get() {
        return ResponseEntity.ok(categoriaRepositorio.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> get(@PathVariable long id) {
        return ResponseEntity.of(categoriaRepositorio.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            excluirCategoria.executar(id);
            return ResponseEntity.noContent().build();
        } catch (ExcecaoDeAplicacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}