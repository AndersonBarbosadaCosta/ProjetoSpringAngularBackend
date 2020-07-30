package com.desafio.nextijr.testeJava.dto;

import com.desafio.nextijr.testeJava.model.Cliente;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClientePedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpf;

    public ClientePedidoDTO() {
    }

    public ClientePedidoDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.cpf = cliente.getCpf();
    }
}
