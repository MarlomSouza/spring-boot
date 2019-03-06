package com.brasilprev.loja.aplicacao.configuracao;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;

public interface Handler<T, Dto> {
    T executar(Dto dto) throws ExcecaoDeAplicacao;
}