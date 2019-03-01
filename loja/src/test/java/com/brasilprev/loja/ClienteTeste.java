package com.brasilprev.loja;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.entidade.clientes.Endereco;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

public class ClienteTeste {

    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;

    @Before
    public void setUp() {
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

        ThrowingCallable act = () -> {
            new Cliente(nome, email, senha, endereco);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome é inválido");
    }

    @Test
    public void nao_deve_criar_com_nome_vazio() {
        nome = null;

        ThrowingCallable act = () -> {
            new Cliente(nome, email, senha, endereco);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome é inválido");
    }

    @Test
    public void nao_deve_criar_com_email_nulo() {
        email = null;

        ThrowingCallable act = () -> {
            new Cliente(nome, email, senha, endereco);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Email é inválido");
    }

    @Test
    public void nao_deve_criar_com_email_vazio() {
        email = " ";

        ThrowingCallable act = () -> {
            new Cliente(nome, email, senha, endereco);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Email é inválido");
    }

    @Test
    public void nao_deve_criar_com_senha_invalida() {
        senha = null;

        ThrowingCallable act = () -> {
            new Cliente(nome, email, senha, endereco);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Senha é inválida");
    }

    @Test
    public void nao_deve_criar_com_senha_vazia() {
        senha = null;

        ThrowingCallable act = () -> {
            new Cliente(nome, email, senha, endereco);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Senha é inválida");
    }

    @Test
    public void nao_deve_criar_com_senha_curta() {
        senha = "123";

        ThrowingCallable act = () -> {
            new Cliente(nome, email, senha, endereco);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class)
                .hasMessageContaining("Senha é muito curta, é necessário ter 6 ou mais caracteres");
    }

    @Test
    public void nao_deve_criar_com_endereco_invalido() {
        endereco = null;

        ThrowingCallable act = () -> {
            new Cliente(nome, email, senha, endereco);
        };

        assertThatThrownBy(act).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Endereço é inválido");
    }
}