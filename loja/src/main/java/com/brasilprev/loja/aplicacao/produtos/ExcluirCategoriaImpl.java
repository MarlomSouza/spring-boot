package com.brasilprev.loja.aplicacao.produtos;

import java.util.List;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirCategoriaImpl implements ExcluirCategoria {

    private CategoriaRepositorio categoriaRepositorio;
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    public ExcluirCategoriaImpl(CategoriaRepositorio categoriaRepositorio, ProdutoRepositorio produtoRepositorio) {
        this.categoriaRepositorio = categoriaRepositorio;
        this.produtoRepositorio = produtoRepositorio;
    }

    @Override
    public Void executar(Long categoriaId) throws ExcecaoDeAplicacao {
        ExcecaoDeAplicacao.Quando(!produtoRepositorio.findByCategoriaId(categoriaId).isEmpty(),
                "Existe produto vinculado a esta categoria");
        categoriaRepositorio.deleteById(categoriaId);
        return null;
    }
}