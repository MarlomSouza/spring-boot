package com.loja;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import com.loja.entidade.clientes.Endereco;
import com.loja.excecao.ExcecaoDeDominio;

import org.junit.Before;
import org.junit.Test;

public class EnderecoTeste {

    private String rua;
    private String cidade;
    private String bairro;
    private String cep;
    private String estado;

    @Before
    public void setUp() {
        rua = "Miranda";
        cidade = "Campo grande";
        bairro = "Palmira";
        cep = "79051-400";
        estado = "MS";
    }

    @Test
    public void deve_criar_endereco() {

        Endereco endereco = new Endereco(rua, bairro, cep, cidade, estado);

        assertEquals(rua, endereco.getRua());
        assertEquals(bairro, endereco.getBairro());
        assertEquals(cep, endereco.getCep());
        assertEquals(cidade, endereco.getCidade());
        assertEquals(estado, endereco.getEstado());
    }

    @Test
    public void nao_deve_criar_com_rua_invalida() {
        rua = null;

        assertThatThrownBy(() -> {
            new Endereco(rua, bairro, cep, cidade, estado);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome da rua é inválido");
    }

    @Test
    public void nao_deve_criar_com_rua_vazia() {
        rua = " ";

        assertThatThrownBy(() -> {
            new Endereco(rua, bairro, cep, cidade, estado);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome da rua é inválido");
    }

    @Test
    public void nao_deve_criar_com_cidade_invalida() {
        cidade = null;

        assertThatThrownBy(() -> {
            new Endereco(rua, bairro, cep, cidade, estado);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome da cidade é inválido");
    }

    @Test
    public void nao_deve_criar_com_cidade_vazia() {
        cidade = " ";

        assertThatThrownBy(() -> {
            new Endereco(rua, bairro, cep, cidade, estado);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome da cidade é inválido");
    }

    @Test
    public void nao_deve_criar_com_bairro_invalido() {
        bairro = null;

        assertThatThrownBy(() -> {
            new Endereco(rua, bairro, cep, cidade, estado);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome do bairro é inválido");
    }

    @Test
    public void nao_deve_criar_com_bairro_vazio() {
        bairro = " ";

        assertThatThrownBy(() -> {
            new Endereco(rua, bairro, cep, cidade, estado);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome do bairro é inválido");
    }

    @Test
    public void nao_deve_criar_com_cep_invalido() {
        cep = null;

        assertThatThrownBy(() -> {
            new Endereco(rua, bairro, cep, cidade, estado);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Número do cep é inválido");
    }

    @Test
    public void nao_deve_criar_com_cep_vazio() {
        cep = " ";

        assertThatThrownBy(() -> {
            new Endereco(rua, bairro, cep, cidade, estado);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Número do cep é inválido");
    }

    @Test
    public void nao_deve_criar_com_estado_invalido() {
        estado = null;

        assertThatThrownBy(() -> {
            new Endereco(rua, bairro, cep, cidade, estado);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome do estado é inválido");
    }

    @Test
    public void nao_deve_criar_com_estado_vazio() {
        estado = " ";

        assertThatThrownBy(() -> {
            new Endereco(rua, bairro, cep, cidade, estado);
        }).isInstanceOf(ExcecaoDeDominio.class).hasMessageContaining("Nome do estado é inválido");
    }

}