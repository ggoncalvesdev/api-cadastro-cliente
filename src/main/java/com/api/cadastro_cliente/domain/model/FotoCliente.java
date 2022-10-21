package com.api.cadastro_cliente.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class FotoCliente {

  @Id
  @Column(name = "cliente_id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY) // a parametro FetchType.LAZY é para evitar Selects que não precisamos no momento
  @MapsId // Anotação MapsId é para indicar que a propriedade Cliente é mapeada pelo Id de fotoCliente
  private Cliente cliente;

  private String nomeArquivo;
  private String contentType;
  private Long tamanho;

  public FotoCliente() {}

  public FotoCliente(
    Long id,
    Cliente cliente,
    String nomeArquivo,
    String contentType,
    Long tamanho
  ) {
    this.id = id;
    this.cliente = cliente;
    this.nomeArquivo = nomeArquivo;
    this.contentType = contentType;
    this.tamanho = tamanho;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

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
