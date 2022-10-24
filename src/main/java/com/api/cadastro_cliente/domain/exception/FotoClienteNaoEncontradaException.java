package com.api.cadastro_cliente.domain.exception;

public class FotoClienteNaoEncontradaException
  extends EntidadeNaoEncontradaException {

  private static final long serialVersionUID = 1L;

  public FotoClienteNaoEncontradaException(String mensagem) {
    super(mensagem);
  }

  public FotoClienteNaoEncontradaException(Long clienteId) {
    this(
      String.format(
        "Não existe um cadastro de foto do cliente com código %d",
        clienteId
      )
    );
  }
}
