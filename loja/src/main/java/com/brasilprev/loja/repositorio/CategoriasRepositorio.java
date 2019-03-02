package com.brasilprev.loja.repositorio;

import com.brasilprev.loja.dominio.entidade.produtos.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepositorio extends JpaRepository<Categoria, Long> {
}