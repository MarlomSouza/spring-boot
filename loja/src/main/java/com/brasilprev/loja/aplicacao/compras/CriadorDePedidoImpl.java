package com.brasilprev.loja.aplicacao.compras;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.entidade.compras.ItemPedido;
import com.brasilprev.loja.dominio.entidade.compras.Pedido;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.dominio.excecao.ExcecaoDeDominio;
import com.brasilprev.loja.repositorio.ClienteRepositorio;
import com.brasilprev.loja.repositorio.PedidoRepositorio;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriadorDePedidoImpl implements CriadorDePedido {

    private final PedidoRepositorio pedidoRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ProdutoRepositorio produtoRepositorio;

    @Autowired
    public CriadorDePedidoImpl(PedidoRepositorio pedidoRepositorio, ClienteRepositorio clienteRepositorio,
            ProdutoRepositorio produtoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.produtoRepositorio = produtoRepositorio;
    }

    @Override
    public Pedido executar(PedidoDto pedidoDto) {
        try {

            Pedido pedido = obterPedido(pedidoDto);
            adicionarItemPedido(pedido, pedidoDto.itensPedido);
            pedidoRepositorio.save(pedido);
            return pedido;
        } catch (ExcecaoDeDominio e) {
            throw new ExcecaoDeAplicacao(e.getMessage());
        }
    }

    private void adicionarItemPedido(Pedido pedido, ItemPedidoDto[] itensPedido) {
        for (ItemPedidoDto itemPedidoDto : itensPedido) {

            Produto produto = produtoRepositorio.findById(itemPedidoDto.produtoId)
                    .orElseThrow(() -> new ExcecaoDeAplicacao("Produto não foi encontrado"));

            produto.removerQuantidade(itemPedidoDto.quantidade);
            ItemPedido itemPedido = new ItemPedido(produto, itemPedidoDto.quantidade);
            pedido.adicionarItemPedido(itemPedido);
            produtoRepositorio.save(produto);
        }
    }

    private Pedido obterPedido(PedidoDto pedidoDto) {
        Cliente cliente = clienteRepositorio.findById(pedidoDto.clienteId)
                .orElseThrow(() -> new ExcecaoDeAplicacao("Cliente não foi encontrado"));

        if (pedidoDto.pedidoId == 0)
            return new Pedido(cliente);

        Pedido pedido = pedidoRepositorio.findById(pedidoDto.pedidoId)
                .orElseThrow(() -> new ExcecaoDeAplicacao("Pedido não foi encontrado"));

        return pedido;
    }

}