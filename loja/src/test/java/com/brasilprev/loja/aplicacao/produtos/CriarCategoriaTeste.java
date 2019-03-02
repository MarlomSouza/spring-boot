package com.brasilprev.loja.aplicacao.produtos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.brasilprev.loja.dominio.entidade.produtos.Categoria;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;

import org.junit.Test;

public class CriarCategoriaTeste {

    @Test
    public void deve_criar() {
        CategoriaRepositorio repositorio = mock(CategoriaRepositorio.class);
        CriarCategoria criarCategoria = new CriarCategoriaImpl(repositorio);
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.nome = "Limpeza";

        Categoria categoria = criarCategoria.criar(categoriaDto);

        verify(repositorio).save(categoria);
    }
}