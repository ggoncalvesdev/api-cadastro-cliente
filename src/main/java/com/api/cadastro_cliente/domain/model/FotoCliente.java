package com.api.cadastro_cliente.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
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
}
