package com.pedido.excecao;

import java.util.ArrayList;

/**
 * ExcecaoDeDominio
 */
public class ExcecaoDeDominio extends RuntimeException{

    ExcecaoDeDominio(String mensagem)
    {
        super(mensagem);
    }

    public static void Validar(Boolean haErro, String mensagemDeErro){
        if(haErro)
            throw new ExcecaoDeDominio(mensagemDeErro);
    }

}