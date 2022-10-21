package com.api.cadastro_cliente.domain.exception;

public class NegocioException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NegocioException(String mensagem) {
    super(mensagem);
  }

  public NegocioException(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }
}
