package com.api.cadastro_cliente.client.controller.model;

public class FotoClienteModel {

  private String nomeArquivo;
  private String contentType;
  private Long tamanho;

  public String getNomeArquivo() {
    return this.nomeArquivo;
  }

  public void setNomeArquivo(String nomeArquivo) {
    this.nomeArquivo = nomeArquivo;
  }

  public String getContentType() {
    return this.contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public Long getTamanho() {
    return this.tamanho;
  }

  public void setTamanho(Long tamanho) {
    this.tamanho = tamanho;
  }
}
