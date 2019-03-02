package com.brasilprev.loja.repositorio;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    Cliente findByNome(String nome);
}
