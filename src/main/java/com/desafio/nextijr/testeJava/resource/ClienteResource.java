package com.desafio.nextijr.testeJava.resource;

import com.desafio.nextijr.testeJava.conversor.ClienteConversor;
import com.desafio.nextijr.testeJava.dto.ClienteDTO;
import com.desafio.nextijr.testeJava.model.Cliente;
import com.desafio.nextijr.testeJava.service.ClienteService;
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
@RequestMapping("/api/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteConversor conversor;

    @ApiOperation(value = "Busca cliente por id", response = Cliente.class)
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Long id) {
        final Cliente cliente = this.service.listarCliente(id);
        return ResponseEntity.ok().body(cliente);
    }

    //http://localhost:8080/clientes/cpf?value=01184507000
    @ApiOperation(value = "Busca cliente por cpf", response = Cliente.class)
    @GetMapping("/cpf")
    public ResponseEntity<Cliente> buscarCpf(@RequestParam(value = "value") String cpf) {
        final Cliente cliente = this.service.buscarCpf(cpf);
        return ResponseEntity.ok().body(cliente);
    }

    @ApiOperation(value = "Insere cliente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> inserir(@Valid @RequestBody ClienteDTO objDto) {
        Cliente cliente = this.conversor.converterDtoParaEntidade(objDto);
        cliente = this.service.salvar(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Altera cliente")
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Long id) {
        var cliente = conversor.converterDtoParaEntidade(objDto);
        cliente.setId(id);
        this.service.atualizar(cliente);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Remove cliente")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Busca todos cliente", response = ClienteDTO.class)
    @GetMapping
    public List<ClienteDTO> findAll() {
        return this.conversor.converterEntidadesParaDtos(this.service.listarTodos());
    }

    // http://localhost:8080/clientes/page?linesPerPage=2&page=3&direction=DESC&orderBy=id
    // Caso nao escolhido os atributos no param, será setado os valores default
    @ApiOperation(value = "Busca todos os clientes por busca paginada", response = ClienteDTO.class)
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> listaClientes = this.service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listaDto = listaClientes.map(obj -> new ClienteDTO(obj));

        return ResponseEntity.ok().body(listaDto);
    }
}