package com.api.cadastro_cliente.client.assembler;

import com.api.cadastro_cliente.client.controller.model.FotoClienteModel;
import com.api.cadastro_cliente.domain.model.FotoCliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoClienteModelAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public FotoClienteModel toModel(FotoCliente foto) {
    return modelMapper.map(foto, FotoClienteModel.class);
  }
}
