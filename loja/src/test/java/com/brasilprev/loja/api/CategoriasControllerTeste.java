package com.brasilprev.loja.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.brasilprev.loja.api.controller.CategoriasController;
import com.brasilprev.loja.aplicacao.produtos.CategoriaDto;
import com.brasilprev.loja.aplicacao.produtos.CriadorDeCategoria;
import com.brasilprev.loja.dominio.entidade.produtos.Categoria;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CategoriasControllerTeste {

    @Spy
    private List<Categoria> categorias = new ArrayList<Categoria>();
    private CategoriasController categoriasController;
    private CategoriaRepositorio categoriaRepositorio;
    private CriadorDeCategoria criadorDeCategoria;

    @Before
    public void setUp() {
        criadorDeCategoria = mock(CriadorDeCategoria.class);
        categoriaRepositorio = mock(CategoriaRepositorio.class);
        categoriasController = new CategoriasController(categoriaRepositorio, criadorDeCategoria);
    }

    @Test
    public void deve_criar() {
        final long categoriaId = 4;
        final String localizacaoCriado = "api/categorias/" + categoriaId;
        Categoria categoria = mock(Categoria.class);
        CategoriaDto categoriaDto = mock(CategoriaDto.class);
        when(categoria.getId()).thenReturn(categoriaId);
        when(criadorDeCategoria.criar(categoriaDto)).thenReturn(categoria);

        ResponseEntity<?> response = categoriasController.post(categoriaDto);

        assertEquals(localizacaoCriado, response.getHeaders().getLocation().getPath());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void deve_obter_todas() {
        when(categoriaRepositorio.findAll()).thenReturn(categorias);

        ResponseEntity<List<Categoria>> response = categoriasController.get();

        assertEquals(categorias, response.getBody());
    }

    @Test
    public void deve_obter_por_id() {
        final long categoriaId = 7;
        Categoria categoria = mock(Categoria.class);
        when(categoriaRepositorio.findById(categoriaId)).thenReturn(Optional.of(categoria));

        ResponseEntity<Categoria> response = categoriasController.get(categoriaId);

        assertEquals(categoria, response.getBody());
    }

    @Test
    public void deve_retornar_nao_encontrado() {
        final long categoriaId = 0;

        ResponseEntity<Categoria> response = categoriasController.get(categoriaId);

        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}