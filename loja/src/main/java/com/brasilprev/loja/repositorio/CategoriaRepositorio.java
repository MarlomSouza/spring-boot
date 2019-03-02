package com.brasilprev.loja.repositorio;

import com.brasilprev.loja.dominio.entidade.produtos.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
}