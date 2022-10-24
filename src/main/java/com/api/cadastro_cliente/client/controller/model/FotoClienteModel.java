package com.api.cadastro_cliente.client.controller.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoClienteModel {

  private String nomeArquivo;
  private String contentType;
  private Long tamanho;
}
