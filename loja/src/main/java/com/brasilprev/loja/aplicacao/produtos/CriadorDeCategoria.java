package com.brasilprev.loja.aplicacao.produtos;

import com.brasilprev.loja.dominio.entidade.produtos.Categoria;

public interface CriadorDeCategoria {
    Categoria criar(CategoriaDto categoriaDto);
}
