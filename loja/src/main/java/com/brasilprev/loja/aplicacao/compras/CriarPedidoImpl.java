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
public class CriarPedidoImpl implements CriarPedido {

    private PedidoRepositorio pedidoRepositorio;
    private ClienteRepositorio clienteRepositorio;
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    public CriarPedidoImpl(PedidoRepositorio pedidoRepositorio, ClienteRepositorio clienteRepositorio,
            ProdutoRepositorio produtoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.produtoRepositorio = produtoRepositorio;
    }

    @Override
    public Pedido criar(PedidoDto pedidoDto) {
        Pedido pedido = obterPedido(pedidoDto);

        Produto produto = produtoRepositorio.findById(pedidoDto.produtoId).orElse(null);

        ExcecaoDeAplicacao.Quando(produto == null, "Produto não foi encontrado");

        ItemPedido itemPedido = new ItemPedido(pedido, produto, pedidoDto.quantidade);
        pedido.adicionarItemPedido(itemPedido);

        pedidoRepositorio.save(pedido);
        return pedido;
    }

    private Pedido obterPedido(PedidoDto pedidoDto) {
        Cliente cliente = clienteRepositorio.findById(pedidoDto.clienteId).orElse(null);

        ExcecaoDeAplicacao.Quando(cliente == null, "Cliente não foi encontrado");

        if (pedidoDto.pedidoId == 0)
            return new Pedido(cliente);

        Pedido pedido = pedidoRepositorio.findById(pedidoDto.pedidoId).orElse(null);
        ExcecaoDeAplicacao.Quando(pedido == null, "Pedido não foi encontrado");

        return pedido;
    }

}