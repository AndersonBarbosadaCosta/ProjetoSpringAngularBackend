package com.desafio.nextijr.testeJava.dto;

import com.desafio.nextijr.testeJava.model.Cliente;

import java.io.Serializable;

public class ClientePedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String cpf;

    public ClientePedidoDTO() {
    }

    public ClientePedidoDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.cpf = cliente.getCpf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
