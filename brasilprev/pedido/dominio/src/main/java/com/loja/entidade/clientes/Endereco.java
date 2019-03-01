package com.loja.entidade.clientes;

import com.loja.entidade.Entidade;
import com.loja.excecao.ExcecaoDeDominio;

public class Endereco extends Entidade {

    private String rua;
    private String cidade;
    private String bairro;
    private String cep;
    private String estado;

    public Endereco(String rua, String bairro, String cep, String cidade, String estado) {
        validar(rua, bairro, cep, cidade, estado);

        this.rua = rua;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.estado = estado;
    }

    private static void validar(String rua, String bairro, String cep, String cidade, String estado) {
        ExcecaoDeDominio.Quando(rua == null || rua.trim().isEmpty(), "Nome da rua é inválido");
        ExcecaoDeDominio.Quando(bairro == null || bairro.trim().isEmpty(), "Nome do bairro é inválido");
        ExcecaoDeDominio.Quando(cep == null || cep.trim().isEmpty(), "Número do cep é inválido");
        ExcecaoDeDominio.Quando(cidade == null || cidade.trim().isEmpty(), "Nome da cidade é inválido");
        ExcecaoDeDominio.Quando(estado == null || estado.trim().isEmpty(), "Nome do estado é inválido");
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

}