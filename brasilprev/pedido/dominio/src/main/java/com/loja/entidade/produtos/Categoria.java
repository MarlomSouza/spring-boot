package com.loja.entidade.produtos;

import com.loja.entidade.Entidade;
import com.loja.excecao.ExcecaoDeDominio;

public class Categoria extends Entidade {

    private String nome;

    public Categoria(String nome) {
        ExcecaoDeDominio.Quando(nome == null || nome.trim().isEmpty(), "Nome é inválido");

        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}