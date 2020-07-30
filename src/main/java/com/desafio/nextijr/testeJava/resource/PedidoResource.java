package com.desafio.nextijr.testeJava.resource;

import com.desafio.nextijr.testeJava.conversor.PedidoConversor;
import com.desafio.nextijr.testeJava.dto.PedidoDTO;
import com.desafio.nextijr.testeJava.model.Pedido;
import com.desafio.nextijr.testeJava.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @Autowired
    private PedidoConversor conversor;

    @ApiOperation(value = "Busca todos pedidos", response = PedidoDTO.class)
    @GetMapping
    public List<PedidoDTO> findAll() {

        return this.conversor.converterEntidadesParaDtos(this.service.listarTodos());
    }

    @ApiOperation(value = "Busca pedido por id", response = PedidoDTO.class)
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> find(@PathVariable Long id) {
        final Pedido pedido = service.buscar(id);
        PedidoDTO pedidoDTO = new PedidoDTO(pedido);
        return ResponseEntity.ok().body(pedidoDTO);
    }

    @ApiOperation(value = "Insere pedido")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
        obj = service.inserir(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Altera pedido")
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@Valid @RequestBody PedidoDTO objDto, @PathVariable Long id) {
        var pedido = conversor.converterDtoParaEntidade(objDto);
        pedido.setId(id);
        this.service.atualizar(pedido);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Remove pedido")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
