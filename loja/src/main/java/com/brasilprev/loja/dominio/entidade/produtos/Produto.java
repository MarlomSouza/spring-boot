package com.brasilprev.loja.dominio.entidade.produtos;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.brasilprev.loja.dominio.entidade.Entidade;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

@Entity
public class Produto extends Entidade {

    private String nome;
    private String descricao;
    private String foto;
    private BigDecimal preco;
    private int quantidade;
    @OneToOne
    private Categoria categoria;

    private Produto() {
    }

    public Produto(String nome, String descricao, String foto, BigDecimal preco, int quantidade, Categoria categoria) {
        validar(nome, descricao, foto, preco, quantidade, categoria);

        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    private static void validar(String nome, String descricao, String foto, BigDecimal preco, int quantidade,
            Categoria categoria) {
        ExcecaoDeDominio.Quando(nome == null || nome.trim().isEmpty(), "Nome é inválido");
        ExcecaoDeDominio.Quando(descricao == null || descricao.trim().isEmpty(), "Descrição é inválida");
        ExcecaoDeDominio.Quando(foto == null || foto.trim().isEmpty(), "Foto é inválida");
        ExcecaoDeDominio.Quando(preco == null || preco.compareTo(BigDecimal.ZERO) <= 0, "Preço é inválido");
        ExcecaoDeDominio.Quando(quantidade <= 0, "Quantidade é inválida");
        ExcecaoDeDominio.Quando(categoria == null, "Categoria é inválida");
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFoto() {
        return foto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void removerQuantidade(int quantidade) {
        ExcecaoDeDominio.Quando(quantidade > this.quantidade, "Não há quantidade em estoque suficiente");

        this.quantidade -= quantidade;
    }
}