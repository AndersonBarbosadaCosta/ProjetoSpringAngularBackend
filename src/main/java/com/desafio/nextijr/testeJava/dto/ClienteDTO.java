package com.desafio.nextijr.testeJava.dto;

import com.desafio.nextijr.testeJava.model.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpf;
    @NotEmpty(message = "Preenchimento obrigatório")
    private LocalDate dtNascimento;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.dtNascimento = cliente.getDtNascimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
}
