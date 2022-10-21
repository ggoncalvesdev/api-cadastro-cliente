package com.api.cadastro_cliente.infra;

import com.api.cadastro_cliente.domain.model.FotoCliente;
import com.api.cadastro_cliente.domain.repository.ClienteRepositoryQueries;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepositoryQueries {

  @PersistenceContext
  private EntityManager manager;

  @Transactional
  @Override
  public FotoCliente save(FotoCliente foto) {
    return manager.merge(foto);
  }

  @Transactional
  @Override
  public void delete(FotoCliente foto) {
    manager.remove(foto);
  }
}
