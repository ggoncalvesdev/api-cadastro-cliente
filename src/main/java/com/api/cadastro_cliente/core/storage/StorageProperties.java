package com.api.cadastro_cliente.core.storage;

import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class StorageProperties {

  private Local local = new Local();
  private TipoStorage tipo = TipoStorage.LOCAL;

  public enum TipoStorage {
    LOCAL,
  }

  public class Local {

    private Path diretorioFotos;
  }
}
