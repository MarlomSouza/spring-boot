package com.brasilprev.loja.aplicacao.configuracao;

public interface Handler<T, Dto> {
    T executar(Dto dto);
}