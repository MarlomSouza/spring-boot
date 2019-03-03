package com.brasilprev.loja.aplicacao.produtos;

import com.brasilprev.loja.dominio.entidade.produtos.Produto;

public interface CriadorDeProduto {

    Produto criar(ProdutoDto produtoDto);
}