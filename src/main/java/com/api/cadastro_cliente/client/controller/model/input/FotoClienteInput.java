package com.api.cadastro_cliente.client.controller.model.input;

import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class FotoClienteInput {

  @NotNull
  private MultipartFile arquivo;

  public MultipartFile getArquivo() {
    return this.arquivo;
  }

  public void setArquivo(MultipartFile arquivo) {
    this.arquivo = arquivo;
  }
}
