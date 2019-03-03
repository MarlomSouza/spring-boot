package com.brasilprev.loja.aplicacao.produtos;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.dominio.entidade.produtos.Categoria;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;

public class CriadorDeProdutoTeste {

    private ProdutoDto produtoDto;
    private CategoriaRepositorio categoriaRepositprio;
    private CriadorDeProduto criarProduto;
    private ProdutoRepositorio produtoRepositorio;

    @Before
    public void setUp() {
        produtoDto = new ProdutoDto();
        produtoDto.nome = "Produto 1";
        produtoDto.descricao = "Descricao do produto 1";
        produtoDto.foto = "Foto do produto 1";
        produtoDto.preco = new BigDecimal(10.3);
        produtoDto.quantidade = 3;
        produtoDto.categoriaId = 1;
        categoriaRepositprio = mock(CategoriaRepositorio.class);
        produtoRepositorio = mock(ProdutoRepositorio.class);
        criarProduto = new CriadorDeProdutoImpl(produtoRepositorio, categoriaRepositprio);
    }

    @Test
    public void deve_criar() {
        String nomeCategoria = "Limpeza";
        Categoria categoria = new Categoria(nomeCategoria);
        when(categoriaRepositprio.findById(produtoDto.categoriaId)).thenReturn(Optional.of(categoria));

        Produto produto = criarProduto.criar(produtoDto);

        verify(produtoRepositorio).save(produto);
    }

    @Test
    public void deve_disparar_excecao_quando_nao_encontrar_categoria() {
        ThrowingCallable act = () -> {
            criarProduto.criar(produtoDto);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeAplicacao.class)
                .hasMessageContaining("Categoria não foi encontrada");
    }
}