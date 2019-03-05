package com.brasilprev.loja.aplicacao.produtos;

import com.brasilprev.loja.dominio.entidade.produtos.Categoria;
import com.brasilprev.loja.repositorio.CategoriaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriadorDeCategoriaImpl implements CriadorDeCategoria {

    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    public CriadorDeCategoriaImpl(CategoriaRepositorio categoriaRepositorio) {
        this.categoriaRepositorio = categoriaRepositorio;
    }

    @Override
    public Categoria executar(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria(categoriaDto.nome);
        categoriaRepositorio.save(categoria);
        return categoria;
    }
}