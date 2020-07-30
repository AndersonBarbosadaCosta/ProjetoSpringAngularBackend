package com.desafio.nextijr.testeJava.repository;

import com.desafio.nextijr.testeJava.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findBySku(String sku);
}
