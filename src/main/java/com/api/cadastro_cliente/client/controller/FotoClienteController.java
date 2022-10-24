package com.api.cadastro_cliente.client.controller;

import com.api.cadastro_cliente.client.assembler.FotoClienteModelAssembler;
import com.api.cadastro_cliente.client.controller.model.FotoClienteModel;
import com.api.cadastro_cliente.client.controller.model.input.FotoClienteInput;
import com.api.cadastro_cliente.domain.exception.EntidadeNaoEncontradaException;
import com.api.cadastro_cliente.domain.model.Cliente;
import com.api.cadastro_cliente.domain.model.FotoCliente;
import com.api.cadastro_cliente.domain.service.ClienteService;
import com.api.cadastro_cliente.domain.service.FotoClienteService;
import com.api.cadastro_cliente.domain.service.FotoStorageService;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
  private FotoClienteService fotoClienteService;

  @Autowired
  private FotoStorageService fotoStorage;

  /**
   * Metodo Get para buscar um json das informações da imagem.
   * @param clienteId
   * @return Json de informações da imagem.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public FotoClienteModel buscar(@PathVariable Long clienteId) {
    FotoCliente fotoCliente = fotoClienteService.buscarOuFalhar(clienteId);

    return fotoClienteModelAssembler.toModel(fotoCliente);
  }

  /**
   * Metodo Put para atualizar/postar uma imagem
   * @param clienteId
   * @return atualizar/postar uma imagem.
   */
  @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public FotoClienteModel atualizarFoto(
    @PathVariable Long clienteId,
    @Valid FotoClienteInput fotoClienteInput
  )
    throws IOException {
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

    FotoCliente fotoSalva = fotoClienteService.salvar(
      foto,
      arquivo.getInputStream()
    );

    /**
     * Usamos o ModelMapper para converter FotoCliente em FotoClienteModel
     */
    return fotoClienteModelAssembler.toModel(fotoSalva);
  }

  /**
   * Metodo Get para recupercar imagens JPEG
   * @param clienteId
   * @return uma imagem serializada.
   * @throws HttpMediaTypeNotAcceptableException
   */
  @GetMapping
  public ResponseEntity<InputStreamResource> servir(
    @PathVariable Long clienteId,
    @RequestHeader(name = "accept") String acceptHeader
  )
    throws HttpMediaTypeNotAcceptableException {
    try {
      FotoCliente fotoCliente = fotoClienteService.buscarOuFalhar(clienteId);
      /**
       * Pegar o contentType da foto cadastrada
       */
      MediaType mediaTypeFoto = MediaType.parseMediaType(
        fotoCliente.getContentType()
      );
      List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(
        acceptHeader
      );
      /**
       * Verificar compatibilidade da mediaType da foto cadastrada com uma lista de mediaType que o consumidor está passando.
       */
      verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

      InputStream inputStream = fotoStorage.recuperar(
        fotoCliente.getNomeArquivo()
      );

      return ResponseEntity
        .ok()
        .contentType(mediaTypeFoto)
        .body(new InputStreamResource(inputStream));
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.notFound().build();
    }
  }

  private void verificarCompatibilidadeMediaType(
    MediaType mediaTypeFoto,
    List<MediaType> mediaTypesAceitas
  )
    throws HttpMediaTypeNotAcceptableException {
    /**
     * Verifica se pelo menos 1 for compativél retorna true
     */
    boolean compativel = mediaTypesAceitas
      .stream()
      .anyMatch(
        mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto)
      );

    if (!compativel) {
      throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
    }
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long clienteId) {
    fotoClienteService.excluir(clienteId);
  }
}
