package com.api.cadastro_cliente.domain.service;

import com.api.cadastro_cliente.domain.exception.ClienteNaoEncontradoException;
import com.api.cadastro_cliente.domain.model.Cliente;
import com.api.cadastro_cliente.domain.repository.ClienteRepository;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.faces.event.AjaxBehaviorEvent;
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

  public void pesquisaCep(AjaxBehaviorEvent event) {
    try{
      Cliente cliente = new Cliente();
      System.out.println("Cep digitado: " + cliente.getCep());
      URL url = new URL("https://viacep.com.br/ws/"+ cliente.getCep() +"/json/");
      URLConnection connection = url.openConnection();
      InputStream is = connection.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

      String cep = "";
      StringBuilder jsonCep = new StringBuilder();

      while((cep = br.readLine()) != null){
          jsonCep.append(cep);
      }
      
      Cliente clienteCep = new Gson().fromJson(jsonCep.toString(), Cliente.class);

      cliente.setCep(clienteCep.getCep());
      cliente.setLogradouro(clienteCep.getLogradouro());
      cliente.setComplemento(clienteCep.getComplemento());
      cliente.setBairro(clienteCep.getBairro());
      cliente.setLocalidade(clienteCep.getLocalidade());
      cliente.setUf(clienteCep.getUf());
      cliente.setUnidade(clienteCep.getUnidade());
      cliente.setIbge(clienteCep.getIbge());
      cliente.setGia(clienteCep.getGia());
      
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
