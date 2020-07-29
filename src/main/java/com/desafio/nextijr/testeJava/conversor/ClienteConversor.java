package com.desafio.nextijr.testeJava.conversor;

import com.desafio.nextijr.testeJava.dto.ClienteDTO;
import com.desafio.nextijr.testeJava.model.Cliente;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ClienteConversor extends ConversorBase<Cliente, ClienteDTO> {
    @Override
    public ClienteDTO converterEntidadeParaDto(Cliente entidade) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Cliente, ClienteDTO>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(entidade, ClienteDTO.class);
    }

    @Override
    public Cliente converterDtoParaEntidade(ClienteDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<ClienteDTO, Cliente>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(dto, Cliente.class);
    }
}
