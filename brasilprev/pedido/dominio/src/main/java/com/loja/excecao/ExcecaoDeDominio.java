package com.loja.excecao;

/**
 * ExcecaoDeDominio
 */
public class ExcecaoDeDominio extends RuntimeException{

    private static final long serialVersionUID = 1L;

    ExcecaoDeDominio(String mensagem)
    {
        super(mensagem);
    }

    public static void Quando(Boolean haErro, String mensagemDeErro){
        if(haErro)
            throw new ExcecaoDeDominio(mensagemDeErro);
    }

}