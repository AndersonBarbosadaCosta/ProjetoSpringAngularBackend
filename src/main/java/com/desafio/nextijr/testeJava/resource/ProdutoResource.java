package com.desafio.nextijr.testeJava.resource;

import com.desafio.nextijr.testeJava.conversor.ProdutoConversor;
import com.desafio.nextijr.testeJava.dto.ProdutoDTO;
import com.desafio.nextijr.testeJava.model.Produto;
import com.desafio.nextijr.testeJava.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoConversor conversor;

    @ApiOperation(value = "Busca produto por id", response = Produto.class)
    @GetMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable Long id) {
        final Produto produto = service.buscarProduto(id);
        return ResponseEntity.ok().body(produto);
    }

    @ApiOperation(value = "Busca todos produtos", response = ProdutoDTO.class)
    @GetMapping
    public List<ProdutoDTO> findAll() {
        return this.conversor.converterEntidadesParaDtos(this.service.listarTodos());
    }

    @ApiOperation(value = "Insere produto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> inserir(@Valid @RequestBody ProdutoDTO objDto) {
        Produto produto = this.conversor.converterDtoParaEntidade(objDto);
        produto = this.service.salvar(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Altera produto")
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@Valid @RequestBody ProdutoDTO objDto, @PathVariable Long id) {
        var produto = conversor.converterDtoParaEntidade(objDto);
        produto.setId(id);
        this.service.atualizar(produto);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Remove produto")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Busca todos os produtos por busca paginada", response = ProdutoDTO.class)
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        Page<Produto> listaProdutos = this.service.findPage(page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listaDto = listaProdutos.map(obj -> new ProdutoDTO(obj));

        return ResponseEntity.ok().body(listaDto);
    }
}
