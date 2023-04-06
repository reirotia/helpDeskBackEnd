package com.reinaldo.cadcliente.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reinaldo.cadcliente.api.model.ChamadoDTO;
import com.reinaldo.cadcliente.domain.model.Chamado;
import com.reinaldo.cadcliente.domain.service.ChamadoService;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;
	
	@GetMapping("/{id}")
	public ChamadoDTO buscarPorId(@PathVariable Integer id) {
		
		Chamado chamado = chamadoService.buscarPorId(id);
		return new ChamadoDTO(chamado);

	}
	
	@GetMapping
	public List<ChamadoDTO> buscarTodos() {
		
		List<Chamado> chamados = chamadoService.buscarTodos();
		List<ChamadoDTO> chamadosDTO = chamados.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());  
		return chamadosDTO;

	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> salvar(@Valid @RequestBody ChamadoDTO chamdoDTO){
		Chamado chamado = chamadoService.salvar(chamdoDTO); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(chamado.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ChamadoDTO> atualizar(@PathVariable Integer id, @Valid  @RequestBody ChamadoDTO chamdoDTO){
		Chamado chamado = chamadoService.atualizar(id, chamdoDTO); 
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	
	}

}
