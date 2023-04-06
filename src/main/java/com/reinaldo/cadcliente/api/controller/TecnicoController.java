package com.reinaldo.cadcliente.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.cadcliente.api.model.TecnicoDTO;
import com.reinaldo.cadcliente.domain.model.Tecnico;
import com.reinaldo.cadcliente.domain.service.TecnicoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;
	
	

	@GetMapping("/{id}")
	public TecnicoDTO buscarPorId(@PathVariable Integer id) {
		Tecnico tecnico = tecnicoService.buscarPorId(id);

		return new TecnicoDTO(tecnico);
	}

	@GetMapping
	public List<TecnicoDTO> buscarTodos() {
		List<Tecnico> tecnicos = tecnicoService.buscarTodos();
		List<TecnicoDTO> tecnicosDTO = tecnicos.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return tecnicosDTO;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TecnicoDTO salvar(@Valid @RequestBody TecnicoDTO obj) {
		Tecnico tecnico = tecnicoService.salvar(obj);
		//URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId())
			//	.toUri();
		return new TecnicoDTO(tecnico);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TecnicoDTO atualizar(@Valid @PathVariable Integer id, @RequestBody TecnicoDTO obj) {
		Tecnico tecnico = tecnicoService.atualizar(id, obj);
		return new TecnicoDTO(tecnico);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		tecnicoService.delete(id);
	}

}
