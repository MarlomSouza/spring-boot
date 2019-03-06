package com.brasilprev.loja.aplicacao.produtos;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

public class ExcluirCategoriaTeste {

    @Spy
    Produto[] produtos = new Produto[] { mock(Produto.class) };
    private CategoriaRepositorio categoriaRepositorio;
    private ExcluirCategoriaImpl excluirCategoria;
    private ProdutoRepositorio produtoRepositorio;

    @Before
    public void setUp() {
        categoriaRepositorio = mock(CategoriaRepositorio.class);
        produtoRepositorio = mock(ProdutoRepositorio.class);
        excluirCategoria = new ExcluirCategoriaImpl(categoriaRepositorio, produtoRepositorio);
    }

    @Test
    public void deve_excluir() {
        final long categoriaId = 4;

        excluirCategoria.executar(categoriaId);

        verify(categoriaRepositorio).deleteById(categoriaId);
    }

    @Test
    public void deve_disparar_excecao_quando_possuir_produto_utilizando_a_categoria() {
        final long categoriaId = 4;
        when(produtoRepositorio.findByCategoriaId(categoriaId)).thenReturn(Arrays.asList(produtos));

        ThrowingCallable act = () -> {
            excluirCategoria.executar(categoriaId);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeAplicacao.class)
                .hasMessageContaining("Existe produto vinculado a esta categoria");
    }

}