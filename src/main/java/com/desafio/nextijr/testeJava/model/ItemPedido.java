package com.desafio.nextijr.testeJava.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Pedido pedido;

    @NotBlank
    @Column(nullable = false)
    private int quantidade;

    @NotBlank
    @Column(nullable = false)
    private Double preco;

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, Pedido pedido, int quantidade) {
        super();
        this.setProduto(produto);
        this.setPedido(pedido);
        this.setQuantidade(quantidade);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(final Produto produto) {
        this.produto = produto;
        if (produto != null && this.preco != null) {
            this.setPreco(produto.getPreco());
        }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(final Pedido pedido) {
        this.pedido = pedido;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(final Double preco) {
        this.preco = preco;
    }

    public Double getSubTotal() {
        return this.preco * this.quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return quantidade == that.quantidade &&
                id.equals(that.id) &&
                produto.equals(that.produto) &&
                pedido.equals(that.pedido) &&
                preco.equals(that.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produto, pedido, quantidade, preco);
    }
}
