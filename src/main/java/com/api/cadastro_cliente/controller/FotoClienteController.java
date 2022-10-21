package com.api.cadastro_cliente.controller;

import com.api.cadastro_cliente.controller.input.FotoClienteInput;
import java.nio.file.Path;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes/{clienteId}/foto")
public class FotoClienteController {

  @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void atualizarFoto(
    @PathVariable Long clienteId,
    @Valid FotoClienteInput fotoClienteInput
  ) {
    var nomeArquivo =
      UUID.randomUUID().toString() +
      "_" +
      fotoClienteInput.getArquivo().getOriginalFilename();

    var arquivoFoto = Path.of("C:/foto-cliente", nomeArquivo);

    System.out.println(arquivoFoto);
    System.out.println(fotoClienteInput.getArquivo().getContentType());

    try {
      fotoClienteInput.getArquivo().transferTo(arquivoFoto);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
