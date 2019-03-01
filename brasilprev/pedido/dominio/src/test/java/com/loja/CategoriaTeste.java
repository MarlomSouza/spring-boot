package com.loja;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import com.loja.entidade.produtos.Categoria;
import com.loja.excecao.ExcecaoDeDominio;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
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
        ThrowingCallable act = () -> {
            new Categoria(" ");
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome é inválido");
    }

    @Test
    public void nao_deve_criar_com_nome_vazio() {
        ThrowingCallable act = () -> {
            new Categoria(null);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome é inválido");
    }
}