package com.brasilprev.loja.aplicacao.produtos;

import com.brasilprev.loja.dominio.entidade.produtos.Categoria;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;

/**
 * CriarCategoriaImpl
 */
public class CriarCategoriaImpl implements CriarCategoria {

    private CategoriaRepositorio categoriaRepositorio;

    public CriarCategoriaImpl(CategoriaRepositorio categoriaRepositorio) {
        super();
        this.categoriaRepositorio = categoriaRepositorio;
    }

    @Override
    public Categoria criar(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria(categoriaDto.nome);
        categoriaRepositorio.save(categoria);
        return categoria;
    }

}