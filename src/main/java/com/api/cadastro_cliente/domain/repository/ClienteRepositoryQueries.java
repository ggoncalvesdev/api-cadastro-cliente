package com.api.cadastro_cliente.domain.repository;

import com.api.cadastro_cliente.domain.model.FotoCliente;

/**
 * Interface foi criada para adicionar uma abstração para as queries.
 *
 */
public interface ClienteRepositoryQueries {
  FotoCliente save(FotoCliente foto);

  void delete(FotoCliente foto);
}
