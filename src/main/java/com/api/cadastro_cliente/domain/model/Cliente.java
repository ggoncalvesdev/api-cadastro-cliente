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

  /* private Endereco endereco; */
  public Cliente() {}

  public Cliente(
    Long id,
    String nome,
    LocalDate date
    /*,  Endereco endereco */
  ) {
    this.id = id;
    this.nome = nome;
    this.date = date;
    /*     this.endereco = endereco; */
  }

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
