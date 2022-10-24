package com.api.cadastro_cliente.infra.service.storage;

import com.api.cadastro_cliente.domain.service.FotoStorageService;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

/**
 * Classe feita para armazenar fotoCliente.
 */
@Service
public class LocalFotoStorageService implements FotoStorageService {

  /**
   * Caminho do diretorio de fotos
   */
  @Value("${cadastro_cliente.storage.local.diretorio-fotos}")
  private Path diretorioFotos;

  @Override
  public InputStream recuperar(String nomeArquivo) {
    try {
      Path arquivoPath = getArquivoPath(nomeArquivo);

      return Files.newInputStream(arquivoPath);
    } catch (Exception e) {
      throw new StorageException("Não foi possível recuperar arquivo.", e);
    }
  }

  @Override
  public void armazenar(NovaFoto novaFoto) {
    try {
      /**
       * arquivoPath para indicar em qual pasta queremos armazenar os arquivos/foto.
       */
      Path arquivoPath = getArquivoPath(novaFoto.getNomeAquivo());

      /**
       * novaFoto.getInputStream() é o local a onde está a foto,
       * Files.newOutputStream(arquivoPath) é o destino para a onde queremos armazenar a foto.
       */
      FileCopyUtils.copy(
        novaFoto.getInputStream(),
        Files.newOutputStream(arquivoPath)
      );
    } catch (Exception e) {
      throw new StorageException("Não foi possível armazenar arquivo.", e);
    }
  }

  @Override
  public void remover(String nomeArquivo) {
    try {
      /**
       * Pegando o caminho da foto que queremos remover.
       */
      Path arquivoPath = getArquivoPath(nomeArquivo);

      Files.deleteIfExists(arquivoPath);
    } catch (Exception e) {
      throw new StorageException("Não foi possível excluir arquivo.", e);
    }
  }

  /**
   *
   * @param nomeArquivo
   * @return O path do diretorio de fotos
   */
  private Path getArquivoPath(String nomeArquivo) {
    return diretorioFotos.resolve(Path.of(nomeArquivo));
  }
}
