package com.reinaldo.cadcliente.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reinaldo.cadcliente.api.model.ClienteDTO;
import com.reinaldo.cadcliente.domain.model.Cliente;
import com.reinaldo.cadcliente.domain.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService ClienteService; 
	
	@GetMapping("/{id}")
	public ClienteDTO buscarPorId(@PathVariable Integer id) {
		Cliente Cliente = ClienteService.buscarPorId(id); 
		
		return new ClienteDTO(Cliente);
	}
	
	@GetMapping
	public List<ClienteDTO> buscarTodos(){
		List<Cliente> Clientes = ClienteService.buscarTodos();
		List<ClienteDTO> ClientesDTO = Clientes.stream().map( obj -> new ClienteDTO(obj)).collect(Collectors.toList());  
		return ClientesDTO;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO salvar(@Valid @RequestBody ClienteDTO obj) {
		Cliente Cliente =   ClienteService.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Cliente.getId()).toUri();
		return  new ClienteDTO(Cliente);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteDTO atualizar(@Valid @PathVariable Integer id,  @RequestBody ClienteDTO obj) {
		Cliente Cliente =   ClienteService.atualizar(id, obj);
		return  new ClienteDTO(Cliente);

	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		ClienteService.delete(id);
	}


}
