package com.desafio.nextijr.testeJava.conversor;

import com.desafio.nextijr.testeJava.dto.PedidoDTO;
import com.desafio.nextijr.testeJava.model.Pedido;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PedidoConversor extends ConversorBase<Pedido, PedidoDTO> {
    @Override
    public PedidoDTO converterEntidadeParaDto(Pedido entidade) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Pedido, PedidoDTO>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(entidade, PedidoDTO.class);
    }

    @Override
    public Pedido converterDtoParaEntidade(PedidoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<PedidoDTO, Pedido>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(dto, Pedido.class);
    }
}
