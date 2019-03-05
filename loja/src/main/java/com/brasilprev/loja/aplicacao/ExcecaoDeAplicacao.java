package com.brasilprev.loja.aplicacao;

public class ExcecaoDeAplicacao extends RuntimeException {

    private static final long serialVersionUID = 2L;

    public ExcecaoDeAplicacao(String mensagem) {
        super(mensagem);
    }

    public static void Quando(Boolean haErro, String mensagemDeErro) {
        if (haErro)
            throw new ExcecaoDeAplicacao(mensagemDeErro);
    }
}