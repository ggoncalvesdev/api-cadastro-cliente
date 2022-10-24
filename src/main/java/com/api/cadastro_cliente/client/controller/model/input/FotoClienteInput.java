package com.api.cadastro_cliente.client.controller.model.input;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FotoClienteInput {

  @NotNull
  private MultipartFile arquivo;
}
