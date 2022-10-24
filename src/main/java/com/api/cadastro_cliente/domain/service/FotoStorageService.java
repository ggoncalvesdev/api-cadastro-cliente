package com.api.cadastro_cliente.domain.service;

import java.io.InputStream;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

/**
 * Interface criada para aceitar outras implementações futuras de armazenametos.
 *
 */
public interface FotoStorageService {
  InputStream recuperar(String nomeArquivo);

  void armazenar(NovaFoto novaFoto);

  void remover(String nomeArquivo);

  default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
    this.armazenar(novaFoto);

    if (nomeArquivoAntigo != null) {
      this.remover(nomeArquivoAntigo);
    }
  }

  /**
   * Metodo criado para evitar armazenamento de nomes identicos.
   * @param nomeOriginal
   * @return um nome concatenado com um codigo randomico.
   */
  default String gerarNomeArquivo(String nomeOriginal) {
    return UUID.randomUUID().toString() + "_" + nomeOriginal;
  }

  /**
   * Foi criado essa classe interna para não ultilizarmos MultipartFile,
   * porque como MultipartFile vem do pacote springframework.web que tem relação com web
   * e não queriamos colocar na camada de domínio.
   *
   * A anotação @Builder é para facilitar na construção de Objeto.
   */
  @Builder
  @Getter
  class NovaFoto {

    private String nomeAquivo;
    private InputStream inputStream; //IputStream é o fluxo de entrada do arquivo.
  }

  @Builder
  @Getter
  class FotoRecuperada {

    private InputStream inputStream;
    private String url;

    public boolean temUrl() {
      return url != null;
    }

    public boolean temInputStream() {
      return inputStream != null;
    }
  }
}
