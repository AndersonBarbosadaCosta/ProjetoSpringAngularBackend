package com.desafio.nextijr.testeJava.service;

import com.desafio.nextijr.testeJava.model.Produto;
import com.desafio.nextijr.testeJava.repository.ProdutoRepository;
import com.desafio.nextijr.testeJava.exception.ExcecoesNegocio;
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

        return produto.orElseThrow(() -> new ExcecoesNegocio("Produto não encontrado!"));
    }

    public List<Produto> listarTodos() {
        return this.repository.findAll();
    }

    public Produto salvar(Produto produto) {
        var existeProduto = this.repository.findBySku(produto.getSku());
        if (existeProduto != null && !produto.equals(produto)) {
            throw new ExcecoesNegocio("Produto já cadastrado!");
        }
        return this.repository.save(produto);
    }

    public Produto atualizar(Produto produto, Long id) {

        var produtoAtualizar = this.repository.findById(id)
                .orElseThrow(() -> new ExcecoesNegocio("Produto não encontrado!"));
        produto.setId(produtoAtualizar.getId());
        return this.repository.save(produto);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public Produto buscarProdutoSku(String sku) {
        return this.repository.findBySku(sku);
    }

    public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
}
