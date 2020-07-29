package com.desafio.nextijr.testeJava.repository;

import com.desafio.nextijr.testeJava.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
