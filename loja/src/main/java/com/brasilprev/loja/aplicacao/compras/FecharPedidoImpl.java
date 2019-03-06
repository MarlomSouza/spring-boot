package com.brasilprev.loja.aplicacao.compras;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.dominio.entidade.compras.Pedido;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;
import com.brasilprev.loja.repositorio.PedidoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FecharPedidoImpl implements FecharPedido {

    private final PedidoRepositorio pedidoRepositorio;

    @Autowired
    public FecharPedidoImpl(PedidoRepositorio pedidoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
    }

    @Override
    public Pedido executar(Long pedidoId) {
        try {
            Pedido pedido = pedidoRepositorio.findById(pedidoId)
                    .orElseThrow(() -> new ExcecaoDeAplicacao("Pedido não encontrado"));

            pedido.fecharPedido();
            pedidoRepositorio.save(pedido);
            return pedido;
        } catch (ExcecaoDeDominio e) {
            throw new ExcecaoDeAplicacao(e.getMessage());
        }
    }
}