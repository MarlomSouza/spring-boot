package com.pedido.entidade;

public class Cliente {
    public String Nome;
    public String Email;
    public String Senha;

    public Cliente(String nome, String email, String senha) {
        Nome = nome;
        Email = email;
        Senha = senha;
    }

    public String getNome() {
        return Nome;
    }
    public String getEmail() {
        return Email;
    }
    public String getSenha() {
        return Senha;
    }
}