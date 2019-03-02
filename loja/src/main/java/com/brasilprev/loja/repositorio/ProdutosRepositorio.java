package com.brasilprev.loja.repositorio;

import com.brasilprev.loja.dominio.entidade.produtos.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepositorio extends JpaRepository<Produto, Long> {

}