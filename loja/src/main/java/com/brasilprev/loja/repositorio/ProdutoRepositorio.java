package com.brasilprev.loja.repositorio;

import java.util.List;

import com.brasilprev.loja.dominio.entidade.produtos.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaId(long categoriaId);
}