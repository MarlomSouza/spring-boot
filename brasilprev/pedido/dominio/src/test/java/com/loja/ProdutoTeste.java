package com.loja;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import com.loja.entidade.produtos.Categoria;
import com.loja.entidade.produtos.Produto;
import com.loja.excecao.ExcecaoDeDominio;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;

public class ProdutoTeste {

    public String nome;
    public String descricao;
    public String foto;
    public BigDecimal preco;
    public int quantidade;
    private Categoria categoria;

    @Before
    public void setUp() {
        nome = "Produto 1";
        descricao = "Descricao do produto 1";
        foto = "Foto do produto 1";
        preco = new BigDecimal(10.3);
        quantidade = 3;
        categoria = mock(Categoria.class);
    }

    @Test
    public void deve_criar() {
        Produto produto = new Produto(nome, descricao, foto, preco, quantidade, categoria);

        assertEquals(nome, produto.getNome());
        assertEquals(descricao, produto.getDescricao());
        assertEquals(foto, produto.getFoto());
        assertEquals(preco, produto.getPreco());
        assertEquals(quantidade, produto.getQuantidade());
    }

    @Test
    public void nao_deve_criar_com_nome_invalido() {
        nome = " ";

        ThrowingCallable act = () -> {
            new Produto(nome, descricao, foto, preco, quantidade, categoria);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome é inválido");
    }

    @Test
    public void nao_deve_criar_com_nome_vazio() {
        nome = null;

        ThrowingCallable act = () -> {
            new Produto(nome, descricao, foto, preco, quantidade, categoria);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome é inválido");
    }

    @Test
    public void nao_deve_criar_com_descricao_invalida() {
        descricao = " ";

        ThrowingCallable act = () -> {
            new Produto(nome, descricao, foto, preco, quantidade, categoria);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Descrição é inválida");
    }

    @Test
    public void nao_deve_criar_com_descricao_vazia() {
        descricao = null;

        ThrowingCallable act = () -> {
            new Produto(nome, descricao, foto, preco, quantidade, categoria);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Descrição é inválida");
    }

    @Test
    public void nao_deve_criar_com_foto_invalida() {
        foto = " ";

        ThrowingCallable act = () -> {
            new Produto(nome, descricao, foto, preco, quantidade, categoria);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Foto é inválida");
    }

    @Test
    public void nao_deve_criar_com_foto_vazia() {
        foto = null;

        ThrowingCallable act = () -> {
            new Produto(nome, descricao, foto, preco, quantidade, categoria);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Foto é inválida");
    }

    @Test
    public void nao_deve_criar_com_preco_nulo() {
        preco = null;

        ThrowingCallable act = () -> {
            new Produto(nome, descricao, foto, preco, quantidade, categoria);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Preço é inválido");
    }

    @Test
    public void nao_deve_criar_com_preco_invalido() {
        preco = new BigDecimal(-1);

        ThrowingCallable act = () -> {
            new Produto(nome, descricao, foto, preco, quantidade, categoria);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Preço é inválido");
    }

    @Test
    public void nao_deve_criar_com_quantidade_invalido() {
        quantidade = 0;

        ThrowingCallable act = () -> {
            new Produto(nome, descricao, foto, preco, quantidade, categoria);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Quantidade é inválida");
    }

    @Test
    public void nao_deve_criar_com_categoria_invalida() {
        categoria = null;

        ThrowingCallable act = () -> {
            new Produto(nome, descricao, foto, preco, quantidade, categoria);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Categoria é inválida");
    }

    @Test
    public void deve_remover_quantidade() {
        int quantidadeEsperada = 7;
        Produto produto = new Produto(nome, descricao, foto, preco, 10, categoria);

        produto.removerQuantidade(3);

        assertEquals(quantidadeEsperada, produto.getQuantidade());
    }

    @Test
    public void nao_deve_remover_quantidade_superior_a_quantidade_existente() {
        Produto produto = new Produto(nome, descricao, foto, preco, 10, categoria);

        ThrowingCallable act = () -> {
            produto.removerQuantidade(11);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class)
                .hasMessageContaining("Não há quantidade em estoque suficiente");
    }
}
