package com.brasilprev.loja.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.brasilprev.loja.api.controller.CategoriasController;
import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.aplicacao.produtos.CategoriaDto;
import com.brasilprev.loja.aplicacao.produtos.CriadorDeCategoria;
import com.brasilprev.loja.aplicacao.produtos.ExcluirCategoria;
import com.brasilprev.loja.dominio.entidade.produtos.Categoria;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

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
    private ExcluirCategoria excluirCategoria;

    @Before
    public void setUp() {
        criadorDeCategoria = mock(CriadorDeCategoria.class);
        categoriaRepositorio = mock(CategoriaRepositorio.class);
        excluirCategoria = mock(ExcluirCategoria.class);
        categoriasController = new CategoriasController(categoriaRepositorio, criadorDeCategoria, excluirCategoria);
    }

    @Test
    public void deve_criar() {
        final long categoriaId = 4;
        final String localizacaoCriado = "api/categorias/" + categoriaId;
        Categoria categoria = mock(Categoria.class);
        CategoriaDto categoriaDto = mock(CategoriaDto.class);
        when(categoria.getId()).thenReturn(categoriaId);
        when(criadorDeCategoria.executar(categoriaDto)).thenReturn(categoria);

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

    @Test
    public void deve_excluir_um_categoria() {
        final long categoriaId = 3;

        ResponseEntity<?> response = categoriasController.delete(categoriaId);

        verify(excluirCategoria).executar(categoriaId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void nao_deve_excluir_uma_categoria_em_uso() {
        final long categoriaId = 3;
        when(excluirCategoria.executar(categoriaId)).thenThrow(ExcecaoDeAplicacao.class);

        ResponseEntity<?> response = categoriasController.delete(categoriaId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
