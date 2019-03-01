package com.loja;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import com.loja.entidade.produtos.Categoria;
import com.loja.excecao.ExcecaoDeDominio;

import org.junit.Test;

public class CategoriaTeste {

    @Test
    public void deve_criar() {
        String nomeEsperado = "Lacticínios";

        Categoria categoria = new Categoria(nomeEsperado);

        assertEquals(nomeEsperado, categoria.getNome());
    }

    @Test
    public void nao_deve_criar_com_nome_invalido() {
        assertThatThrownBy(() -> {
            new Categoria(" ");
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome é inválido");
    }

    @Test
    public void nao_deve_criar_com_nome_vazio() {
        assertThatThrownBy(() -> {
            new Categoria(null);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome é inválido");
    }
}