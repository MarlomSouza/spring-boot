package com.brasilprev.loja.dominio.entidade.produtos;

import com.brasilprev.loja.dominio.entidade.Entidade;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

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