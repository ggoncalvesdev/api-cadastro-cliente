package com.api.cadastro_cliente.domain.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ClienteNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public ClienteNaoEncontradoException(Long clienteId) {
    this(
      String.format(
        "Não existe um cadastro de cliente com codigo %d",
        clienteId
      )
    );
  }
}
