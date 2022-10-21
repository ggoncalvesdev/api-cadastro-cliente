package com.api.cadastro_cliente.domain.service;

import com.api.cadastro_cliente.domain.model.FotoCliente;
import com.api.cadastro_cliente.domain.repository.ClienteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FotoClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Transactional
  public FotoCliente salvar(FotoCliente foto) {
    /**
     * Pegar o ID de FotoCliente
     */
    Long clienteId = foto.getCliente().getId();

    Optional<FotoCliente> fotoExistente = clienteRepository.findFotoById(
      clienteId
    );
    if (fotoExistente.isPresent()) {
      //exclui se existir foto de cliente com o mesmo Id
      clienteRepository.delete(fotoExistente.get());
    }

    return clienteRepository.save(foto);
  }
}
