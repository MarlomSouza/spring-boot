package com.brasilprev.loja.dominio.entidade.clientes;

import javax.persistence.Embeddable;

import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;

@Embeddable
public class Endereco {

    private String rua;
    private String cidade;
    private String bairro;
    private String cep;
    private String estado;

    private Endereco(){
    }

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

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

}