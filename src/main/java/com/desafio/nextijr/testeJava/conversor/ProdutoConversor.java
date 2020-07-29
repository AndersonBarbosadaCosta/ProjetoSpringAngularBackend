package com.desafio.nextijr.testeJava.conversor;

import com.desafio.nextijr.testeJava.dto.ProdutoDTO;
import com.desafio.nextijr.testeJava.model.Produto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConversor extends ConversorBase<Produto, ProdutoDTO> {


    @Override
    public ProdutoDTO converterEntidadeParaDto(Produto entidade) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Produto, ProdutoDTO>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(entidade, ProdutoDTO.class);
    }

    @Override
    public Produto converterDtoParaEntidade(ProdutoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<ProdutoDTO, Produto>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(dto, Produto.class);
    }
}
