package com.brasilprev.loja.aplicacao;

/**
 * ExcecaoDeAplicacao
 */
public class ExcecaoDeAplicacao extends RuntimeException {

    private static final long serialVersionUID = 1L;

    ExcecaoDeAplicacao(String mensagem)
        {
            super(mensagem);
        }

    public static void Quando(Boolean haErro, String mensagemDeErro) {
        if (haErro)
            throw new ExcecaoDeAplicacao(mensagemDeErro);
    }

}