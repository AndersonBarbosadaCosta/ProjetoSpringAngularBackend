package com.desafio.nextijr.testeJava.service;

import com.desafio.nextijr.testeJava.model.ItemPedido;
import com.desafio.nextijr.testeJava.model.Pedido;
import com.desafio.nextijr.testeJava.repository.ItemPedidoRepository;
import com.desafio.nextijr.testeJava.repository.PedidoRepository;
import com.desafio.nextijr.testeJava.exception.ExcecoesNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    public Pedido buscar(Long id) {
        var pedido = this.pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ExcecoesNegocio("Pedido" + id + " não encontrado"));
    }

    @Transactional
    public Pedido inserir(@Valid Pedido pedido) {

        // pedido.setId(null);
        pedido.setDtPedido(LocalDateTime.now());
        pedido.setCliente(this.clienteService.buscarCpf(pedido.getCliente().getCpf()));

        pedido = this.pedidoRepository.save(pedido);

        for (ItemPedido ip : pedido.getItens()) {
            ip.setProduto(this.produtoService.buscarProduto(ip.getProduto().getId()));
            ip.setPedido(pedido);
            ip.setPreco(ip.getProduto().getPreco());
        }

        this.itemPedidoRepository.saveAll(pedido.getItens());

        return pedido;
    }

    public List<Pedido> listarTodos() {
        return this.pedidoRepository.findAll();
    }

    public Pedido atualizar(Pedido pedido) {

        var product = this.pedidoRepository.findById(pedido.getId())
                .orElseThrow(() -> new ExcecoesNegocio("Pedido não encontrado!"));
        return this.pedidoRepository.save(pedido);
    }

    public void excluir(Long id) {
        this.pedidoRepository.deleteById(id);
    }
}
