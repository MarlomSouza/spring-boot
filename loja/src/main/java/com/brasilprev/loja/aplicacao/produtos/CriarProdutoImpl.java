package com.brasilprev.loja.aplicacao.produtos;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.dominio.entidade.produtos.Categoria;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarProdutoImpl implements CriarProduto {

    private ProdutoRepositorio produtoRepositorio;
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    public CriarProdutoImpl(ProdutoRepositorio produtoRepositorio, CategoriaRepositorio categoriaRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;
    }

    @Override
    public Produto criar(ProdutoDto produtoDto) {
        Categoria categoria = categoriaRepositorio.findById(produtoDto.categoriaId).orElse(null);

        ExcecaoDeAplicacao.Quando(categoria == null, "Categoria não foi encontrada");

        Produto produto = mapearProduto(produtoDto, categoria);
        produtoRepositorio.save(produto);
        return produto;
    }

    private static Produto mapearProduto(ProdutoDto produtoDto, Categoria categoria) {
        return new Produto(produtoDto.nome, produtoDto.descricao, produtoDto.foto, produtoDto.preco,
                produtoDto.quantidade, categoria);
    }
}