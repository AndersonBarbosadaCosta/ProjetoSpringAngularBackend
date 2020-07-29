package com.desafio.nextijr.testeJava.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Double total;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dtPedido;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @ManyToOne
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(LocalDateTime dtPedido) {
        super();
        this.setDtPedido(dtPedido);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(final Double total) {
        this.total = total;
    }

    public LocalDateTime getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(final LocalDateTime dtPedido) {
        this.dtPedido = dtPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(final List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(final Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getValorTotal() {
        return this.itens.stream().mapToDouble(item -> item.getSubTotal()).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id == pedido.id &&
                total.equals(pedido.total) &&
                cliente.equals(pedido.cliente) &&
                dtPedido.equals(pedido.dtPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total, cliente, dtPedido);
    }
}

