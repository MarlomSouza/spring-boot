package com.pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.pedido.entidade.Cliente;

import org.junit.Test;

import junit.framework.Assert;

public class ClienteTeste {

    @Test
    public void Deve_criar_cliente() {
        String nome = "marlom";
        String email = "marlom@gmail.com";
        String senha = "senha.123";

        Cliente cliente = new Cliente(nome, email, senha);
        
        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
        assertEquals(senha, cliente.getSenha());
    }

    @Test
    public void Nao_deve_criar_cliente_sem_nome(){
        
    }
}