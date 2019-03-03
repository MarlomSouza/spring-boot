package com.brasilprev.loja.dominio.entidade.produtos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

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