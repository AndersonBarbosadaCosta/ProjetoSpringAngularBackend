package com.desafio.nextijr.testeJava.service;

import com.desafio.nextijr.testeJava.model.Produto;
import com.desafio.nextijr.testeJava.repository.ProdutoRepository;
import exception.ExecoesNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto buscarProduto(Long id) {
        var produto = repository.findById(id);

        return produto.orElseThrow(() -> new ExecoesNegocio("Cliente não encontrado!"));
    }

    public List<Produto> listarTodos() {
        return this.repository.findAll();
    }

    public Produto salvar(Produto produto) {
        var existeProduto = this.repository.findById(produto.getId())
                .orElseThrow(() -> new ExecoesNegocio("Produto já cadastrado!"));
        return this.repository.save(produto);
    }

    public Produto atualizar(Produto produto) {

        var product = this.repository.findById(produto.getId())
                .orElseThrow(() -> new ExecoesNegocio("Produto não encontrado!"));
        return this.repository.save(product);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
}
