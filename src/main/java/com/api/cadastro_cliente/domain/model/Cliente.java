package com.api.cadastro_cliente.domain.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private LocalDate date;
  
  private String cep;

  private String logradouro;

  private String complemento;

  private String bairro;

  private String localidade;

  private String uf;

  private String unidade;

  private String ibge;

  private String gia;

  public String getCep() {
    return this.cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getLogradouro() {
    return this.logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getComplemento() {
    return this.complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return this.bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getLocalidade() {
    return this.localidade;
  }

  public void setLocalidade(String localidade) {
    this.localidade = localidade;
  }

  public String getUf() {
    return this.uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public String getUnidade() {
    return this.unidade;
  }

  public void setUnidade(String unidade) {
    this.unidade = unidade;
  }

  public String getIbge() {
    return this.ibge;
  }

  public void setIbge(String ibge) {
    this.ibge = ibge;
  }

  public String getGia() {
    return this.gia;
  }

  public void setGia(String gia) {
    this.gia = gia;
  }
  // /* private Endereco endereco; */
  // public Cliente() {}

  // public Cliente(
  //   Long id,
  //   String nome,
  //   LocalDate date
  //   /*,  Endereco endereco */
  // ) {
  //   this.id = id;
  //   this.nome = nome;
  //   this.date = date;
  //   /*     this.endereco = endereco; */
  // }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
  /*   public Endereco getEndereco() {
    return this.endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  } */
}
