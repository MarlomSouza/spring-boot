package com.brasilprev.loja.aplicacao.produtos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.brasilprev.loja.dominio.entidade.produtos.Categoria;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;

import org.junit.Test;

public class CriadorDeCategoriaTeste {

    @Test
    public void deve_criar() {
        CategoriaRepositorio repositorio = mock(CategoriaRepositorio.class);
        CriadorDeCategoria criadorDeCategoria = new CriadorDeCategoriaImpl(repositorio);
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.nome = "Limpeza";

        Categoria categoria = criadorDeCategoria.executar(categoriaDto);

        verify(repositorio).save(categoria);
    }
}