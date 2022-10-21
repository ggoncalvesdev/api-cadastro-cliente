package com.api.cadastro_cliente.controller.input;

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
