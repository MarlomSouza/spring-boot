package com.brasilprev.loja.aplicacao.compras;

import com.brasilprev.loja.aplicacao.ExcecaoDeAplicacao;
import com.brasilprev.loja.dominio.entidade.clientes.Cliente;
import com.brasilprev.loja.dominio.entidade.compras.ItemPedido;
import com.brasilprev.loja.dominio.entidade.compras.Pedido;
import com.brasilprev.loja.dominio.entidade.produtos.Produto;
import com.brasilprev.loja.repositorio.ClienteRepositorio;
import com.brasilprev.loja.repositorio.PedidoRepositorio;
import com.brasilprev.loja.repositorio.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriadorDePedidoImpl implements CriadorDePedido {

    private PedidoRepositorio pedidoRepositorio;
    private ClienteRepositorio clienteRepositorio;
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    public CriadorDePedidoImpl(PedidoRepositorio pedidoRepositorio, ClienteRepositorio clienteRepositorio,
            ProdutoRepositorio produtoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.produtoRepositorio = produtoRepositorio;
    }

    @Override
    public Pedido executar(PedidoDto pedidoDto) {
        Pedido pedido = obterPedido(pedidoDto);
        adicionarItemPedido(pedido, pedidoDto.itensPedido);
        pedidoRepositorio.save(pedido);
        return pedido;
    }

    private void adicionarItemPedido(Pedido pedido, ItemPedidoDto[] itensPedido) {
        for (ItemPedidoDto itemPedidoDto : itensPedido) {

            Produto produto = produtoRepositorio.findById(itemPedidoDto.produtoId)
                    .orElseThrow(() -> new ExcecaoDeAplicacao("Produto não foi encontrado"));

            produto.removerQuantidade(itemPedidoDto.quantidade);
            produtoRepositorio.save(produto);
            ItemPedido itemPedido = new ItemPedido(produto, itemPedidoDto.quantidade);
            pedido.adicionarItemPedido(itemPedido);
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