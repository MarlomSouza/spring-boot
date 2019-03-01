package com.pedido.entidade;

import com.pedido.excecao.ExcecaoDeDominio;

public class Cliente {
    public String nome;
    public String email;
    public String senha;
    private Endereco endereco;

    public Cliente(String nome, String email, String senha, Endereco endereco) {
        validar(nome, email, senha, endereco);

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    private static void validar(String nome, String email, String senha, Endereco endereco) {
        ExcecaoDeDominio.Validar(nome == null || nome.trim().isEmpty(), "Nome é inválido");
        ExcecaoDeDominio.Validar(email == null || email.trim().isEmpty(), "Email é inválido");
        ExcecaoDeDominio.Validar(senha == null || senha.trim().isEmpty(), "Senha é inválida");
        ExcecaoDeDominio.Validar(senha == null || senha.trim().length() < 6,
                "Senha é muito curta, é necessário ter 6 ou mais caracteres");
        ExcecaoDeDominio.Validar(endereco == null, "Endereço é inválido");
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}