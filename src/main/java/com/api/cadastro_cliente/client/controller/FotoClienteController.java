package com.api.cadastro_cliente.client.controller;

import com.api.cadastro_cliente.client.assembler.FotoClienteModelAssembler;
import com.api.cadastro_cliente.client.controller.model.FotoClienteModel;
import com.api.cadastro_cliente.client.controller.model.input.FotoClienteInput;
import com.api.cadastro_cliente.domain.model.Cliente;
import com.api.cadastro_cliente.domain.model.FotoCliente;
import com.api.cadastro_cliente.domain.service.ClienteService;
import com.api.cadastro_cliente.domain.service.FotoClienteService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/clientes/{clienteId}/foto")
public class FotoClienteController {

  @Autowired
  private FotoClienteModelAssembler fotoClienteModelAssembler;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private FotoClienteService fotoCliente;

  @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public FotoClienteModel atualizarFoto(
    @PathVariable Long clienteId,
    @Valid FotoClienteInput fotoClienteInput
  ) {
    Cliente cliente = clienteService.buscarOuFalhar(clienteId);

    /**
     * Para buscar o arquivo e manipular suas propriedades
     */
    MultipartFile arquivo = fotoClienteInput.getArquivo();

    /**
     * instanciar FotoCliente para atribuir as propriedades dentro do objeto.
     */
    FotoCliente foto = new FotoCliente();

    foto.setCliente(cliente);
    foto.setContentType(arquivo.getContentType());
    foto.setTamanho(arquivo.getSize());
    foto.setNomeArquivo(arquivo.getOriginalFilename());

    FotoCliente fotoSalva = fotoCliente.salvar(foto);

    /**
     * Usamos o ModelMapper para converter FotoCliente em FotoClienteModel
     */
    return fotoClienteModelAssembler.toModel(fotoSalva);
  }
}
