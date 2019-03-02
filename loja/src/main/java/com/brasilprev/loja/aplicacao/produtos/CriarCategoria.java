package com.brasilprev.loja.aplicacao.produtos;

import com.brasilprev.loja.dominio.entidade.produtos.Categoria;

public interface CriarCategoria {
    Categoria criar(CategoriaDto categoriaDto);
}
