package com.api.cadastro_cliente.domain.service;

import com.api.cadastro_cliente.domain.exception.FotoClienteNaoEncontradaException;
import com.api.cadastro_cliente.domain.model.FotoCliente;
import com.api.cadastro_cliente.domain.repository.ClienteRepository;
import com.api.cadastro_cliente.domain.service.FotoStorageService.NovaFoto;
import java.io.InputStream;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe feita para salvar fotoCliente
 */
@Service
public class FotoClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private FotoStorageService fotoStorage;

  @Transactional
  public FotoCliente salvar(FotoCliente foto, InputStream dadosArquivo) {
    /**
     * Pegar o ID de FotoCliente
     */
    Long clienteId = foto.getCliente().getId();
    /**
     * Gerar um nome novo do arquivo
     */
    String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(
      foto.getNomeArquivo()
    );
    /**
     * Armazena o nome do arquivo existente.
     */
    String nomeArquivoExistente = null;

    Optional<FotoCliente> fotoExistente = clienteRepository.findFotoById(
      clienteId
    );
    if (fotoExistente.isPresent()) {
      //Pega no nome do arquivo se existir.
      nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
      //Exclui se existir foto de cliente com o mesmo Id
      clienteRepository.delete(fotoExistente.get());
    }
    /**
     * Setando o nome gerado no arquivo
     */
    foto.setNomeArquivo(nomeNovoArquivo);

    /**
     * Primeiro salvamos a foto no repositorio para depois salvar a imagem,
     * caso dê algum problema não ficamos com uma foto aramazenada sem referencia.
     * Flush() é para forçar o JPA a descarregar tudo que está na fila antes de armazenar a foto.
     */
    foto = clienteRepository.save(foto);
    clienteRepository.flush();
    /**
     * Construindo o objeto novaFoto ultilizando a anotação @Builder do projeto lombok.
     */
    NovaFoto novaFoto = NovaFoto
      .builder()
      .nomeAquivo(foto.getNomeArquivo())
      .inputStream(dadosArquivo)
      .build();
    /**
     * Substitui o arquivo ou armazena se não existir.
     */
    fotoStorage.substituir(nomeArquivoExistente, novaFoto);

    return foto;
  }

  @Transactional
  public void excluir(Long clienteId) {
    FotoCliente foto = buscarOuFalhar(clienteId);

    clienteRepository.delete(foto);
    clienteRepository.flush();

    fotoStorage.remover(foto.getNomeArquivo());
  }

  public FotoCliente buscarOuFalhar(Long clienteId) {
    return clienteRepository
      .findFotoById(clienteId)
      .orElseThrow(() -> new FotoClienteNaoEncontradaException(clienteId));
  }
}
