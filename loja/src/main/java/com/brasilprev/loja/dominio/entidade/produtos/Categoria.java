package com.brasilprev.loja.dominio.entidade.produtos;

import javax.persistence.Entity;

import com.brasilprev.loja.dominio.entidade.Entidade;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

@Entity
public class Categoria extends Entidade {

    private String nome;

    private Categoria() {
    }

    public Categoria(String nome) {
        ExcecaoDeDominio.Quando(nome == null || nome.trim().isEmpty(), "Nome é inválido");

        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}