package com.loja;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import com.loja.entidade.Cliente;
import com.loja.entidade.Endereco;
import com.loja.excecao.ExcecaoDeDominio;

import org.junit.Before;
import org.junit.Test;

public class ClienteTeste {

    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;

    @Before
    public void SetUp() {
        nome = "marlom";
        email = "marlom@gmail.com";
        senha = "senha.123";
        endereco = mock(Endereco.class);
    }

    @Test
    public void deve_criar_cliente() {

        Cliente cliente = new Cliente(nome, email, senha, endereco);

        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
        assertEquals(senha, cliente.getSenha());
        assertEquals(endereco, cliente.getEndereco());
    }

    @Test
    public void nao_deve_criar_com_nome_invalido() {
        nome = " ";

        assertThatThrownBy(() -> {
            new Cliente(nome, email, senha, endereco);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome é inválido");
    }

    @Test
    public void nao_deve_criar_com_nome_vazio() {
        nome = null;

        assertThatThrownBy(() -> {
            new Cliente(nome, email, senha, endereco);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome é inválido");
    }

    @Test
    public void nao_deve_criar_com_email_nulo() {
        email = null;

        assertThatThrownBy(() -> {
            new Cliente(nome, email, senha, endereco);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Email é inválido");
    }

    @Test
    public void nao_deve_criar_com_email_vazio() {
        email = " ";

        assertThatThrownBy(() -> {
            new Cliente(nome, email, senha, endereco);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Email é inválido");
    }

    @Test
    public void nao_deve_criar_com_senha_invalida() {
        senha = null;

        assertThatThrownBy(() -> {
            new Cliente(nome, email, senha, endereco);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Senha é inválida");
    }

    @Test
    public void nao_deve_criar_com_senha_vazia() {
        senha = null;

        assertThatThrownBy(() -> {
            new Cliente(nome, email, senha, endereco);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Senha é inválida");
    }

    @Test
    public void nao_deve_criar_com_senha_curta() {
        senha = "123";

        assertThatThrownBy(() -> {
            new Cliente(nome, email, senha, endereco);
        }).isInstanceOf(ExcecaoDeDominio.class)
                .hasMessageContaining("Senha é muito curta, é necessário ter 6 ou mais caracteres");
    }

    @Test
    public void nao_deve_criar_com_endereco_invalido() {
        endereco = null;

        assertThatThrownBy(() -> {
            new Cliente(nome, email, senha, endereco);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Endereço é inválido");
    }
}