package com.brasilprev.loja.api;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.brasilprev.loja.api.controller.ProdutosController;
import com.brasilprev.loja.aplicacao.produtos.CriarProduto;
import com.brasilprev.loja.aplicacao.produtos.ProdutoDto;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProdutosControllerTeste {

    private final List<Produto> produtos = new ArrayList<Produto>();
    private ProdutoRepositorio produtoRepositorio;
    private CriarProduto criarProdutos;
    private ProdutosController produtosController;
    private Produto produto;

    @Before
    public void setUp() {
        produto = mock(Produto.class);
        produtoRepositorio = mock(ProdutoRepositorio.class);
        criarProdutos = mock(CriarProduto.class);
        produtosController = new ProdutosController(produtoRepositorio, criarProdutos);
    }

    @Test
    public void deve_criar() {
        final long produtoId = 9;
        final String localizacaoCriado = "api/produtos/" + produtoId;

        ProdutoDto produtoDto = mock(ProdutoDto.class);
        when(criarProdutos.criar(produtoDto)).thenReturn(produto);
        when(produto.getId()).thenReturn(produtoId);

        ResponseEntity<Produto> response = produtosController.post(produtoDto);

        assertEquals(localizacaoCriado, response.getHeaders().getLocation().getPath());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void deve_obter_todos() {
        when(produtoRepositorio.findAll()).thenReturn(produtos);

        ResponseEntity<List<Produto>> response = produtosController.get();

        assertEquals(produtos, response.getBody());
    }

    @Test
    public void deve_obter_por_id() {
        final long produtoId = 9;
        when(produtoRepositorio.findById(produtoId)).thenReturn(Optional.of(produto));

        ResponseEntity<Produto> response = produtosController.get(produtoId);

        assertEquals(produto, response.getBody());
    }

    @Test
    public void deve_retornar_nao_encontrado() {
        final long produtoId = 9;

        ResponseEntity<Produto> response = produtosController.get(produtoId);

        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}