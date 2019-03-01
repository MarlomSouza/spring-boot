package com.brasilprev.loja.dominio.entidade.clientes;

import com.brasilprev.loja.dominio.entidade.Entidade;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

public class Cliente extends Entidade {

    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;

    public Cliente(String nome, String email, String senha, Endereco endereco) {
        validar(nome, email, senha, endereco);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    private static void validar(String nome, String email, String senha, Endereco endereco) {
        ExcecaoDeDominio.Quando(nome == null || nome.trim().isEmpty(), "Nome é inválido");
        ExcecaoDeDominio.Quando(email == null || email.trim().isEmpty(), "Email é inválido");
        ExcecaoDeDominio.Quando(senha == null || senha.trim().isEmpty(), "Senha é inválida");
        ExcecaoDeDominio.Quando(senha == null || senha.trim().length() < 6,
                "Senha é muito curta, é necessário ter 6 ou mais caracteres");
        ExcecaoDeDominio.Quando(endereco == null, "Endereço é inválido");
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