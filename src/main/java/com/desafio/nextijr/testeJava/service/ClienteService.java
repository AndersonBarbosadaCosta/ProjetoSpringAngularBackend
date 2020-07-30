package com.desafio.nextijr.testeJava.service;

import com.desafio.nextijr.testeJava.model.Cliente;
import com.desafio.nextijr.testeJava.repository.ClienteRepository;
import com.desafio.nextijr.testeJava.exception.ExcecoesNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente listarCliente(Long id) {
        var cliente = repository.findById(id);

        return cliente.orElseThrow(() -> new ExcecoesNegocio("Cliente não encontrado!"));
    }

    public List<Cliente> listarTodos() {
        return this.repository.findAll();
    }

    public Cliente salvar(Cliente cliente) {

        var existeCliente = this.repository.findByCpf(cliente.getCpf());

        if (existeCliente != null && !cliente.equals(cliente)) {
            throw new ExcecoesNegocio("Cpf já cadastrado! ");
        }
        return this.repository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente, Long id) {

        var clienteAtualizar = this.repository.findById(id)
                .orElseThrow(() -> new ExcecoesNegocio("Cliente não encontrado!"));
        cliente.setId(clienteAtualizar.getId());
        return this.repository.save(cliente);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public Cliente buscarCpf(String cpf) {
        return this.repository.findByCpf(cpf);
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
}
