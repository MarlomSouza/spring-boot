package com.brasilprev.loja.repositorio;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClientesRepositorio
 */
public interface ClientesRepositorio extends JpaRepository<Cliente, Long> {
}
