package com.desafio.nextijr.testeJava.dto;

import com.desafio.nextijr.testeJava.model.Cliente;
import com.desafio.nextijr.testeJava.model.ItemPedido;
import com.desafio.nextijr.testeJava.model.Pedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PedidoDTO {

    private Long id;

    private Double total;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dtPedido;

    private Set<ItemPedido> itens = new HashSet<>();

    private ClientePedidoDTO cliente;

    public PedidoDTO() {
    }

    public PedidoDTO(Pedido pedido) {

        this.id = pedido.getId();
        this.dtPedido = pedido.getDtPedido();
        this.cliente = new ClientePedidoDTO(pedido.getCliente());
        this.itens = pedido.getItens();
        this.total = this.getValorTotal();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(LocalDateTime dtPedido) {
        this.dtPedido = dtPedido;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public ClientePedidoDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClientePedidoDTO cliente) {
        this.cliente = cliente;
    }

    public Double getValorTotal() {
        return this.itens.stream().mapToDouble(item -> item.getSubTotal()).sum();
    }
}
