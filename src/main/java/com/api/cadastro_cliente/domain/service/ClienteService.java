package com.api.cadastro_cliente.domain.service;

import com.api.cadastro_cliente.domain.exception.ClienteNaoEncontradoException;
import com.api.cadastro_cliente.domain.model.Cliente;
import com.api.cadastro_cliente.domain.repository.ClienteRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Transactional
  public Cliente salvar(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  @Transactional
  public void excluir(Long clienteId) {
    try {
      clienteRepository.deleteById(clienteId);
    } catch (EmptyResultDataAccessException e) {
      throw new ClienteNaoEncontradoException(clienteId);
    }
  }

  @Transactional
  public Cliente buscarOuFalhar(Long clienteId) {
    return clienteRepository
      .findById(clienteId)
      .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
  }
}
