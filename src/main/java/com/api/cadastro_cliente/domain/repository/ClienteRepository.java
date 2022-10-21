package com.api.cadastro_cliente.domain.repository;

import com.api.cadastro_cliente.domain.model.Cliente;
import com.api.cadastro_cliente.domain.model.FotoCliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository
  extends JpaRepository<Cliente, Long>, ClienteRepositoryQueries {
  /**
   * Metodo para buscar id de cliente
   * @param clienteId
   * @return Id de cliente
   */
  @Query("from FotoCliente f where f.cliente.id = :clienteId")
  Optional<FotoCliente> findFotoById(Long clienteId);
}
