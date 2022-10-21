package com.api.cadastro_cliente.controller;

import com.api.cadastro_cliente.domain.model.Cliente;
import com.api.cadastro_cliente.domain.repository.ClienteRepository;
import com.api.cadastro_cliente.domain.service.ClienteService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ClienteService clienteService;

  @GetMapping
  public List<Cliente> listar() {
    return clienteRepository.findAll();
  }

  @GetMapping("/{clienteId}")
  public Cliente buscar(@PathVariable Long clienteId) {
    return clienteService.buscarOuFalhar(clienteId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente adicionar(@RequestBody @Valid Cliente clienteId) {
    return clienteService.salvar(clienteId);
  }

  @PutMapping("/{clienteId}")
  public Cliente atualizar(
    @PathVariable Long clienteId,
    @RequestBody @Valid Cliente cliente
  ) {
    Cliente clienteAtual = clienteService.buscarOuFalhar(clienteId);

    BeanUtils.copyProperties(cliente, clienteAtual, "id");

    return clienteService.salvar(clienteAtual);
  }

  @DeleteMapping("/{clienteId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long clienteId) {
    clienteService.excluir(clienteId);
  }
}
