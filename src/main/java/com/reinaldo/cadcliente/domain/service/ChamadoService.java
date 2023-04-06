package com.reinaldo.cadcliente.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinaldo.cadcliente.api.model.ChamadoDTO;
import com.reinaldo.cadcliente.domain.enums.Prioridade;
import com.reinaldo.cadcliente.domain.enums.Status;
import com.reinaldo.cadcliente.domain.exception.EntidadeNaoEncontradaException;
import com.reinaldo.cadcliente.domain.model.Chamado;
import com.reinaldo.cadcliente.domain.model.Cliente;
import com.reinaldo.cadcliente.domain.model.Tecnico;
import com.reinaldo.cadcliente.domain.repository.ChamadoRepository;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tenicoService;
	@Autowired
	private ClienteService clienteService;

	public Chamado buscarPorId(Integer id) {
		return chamadoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(id));

	}

	public List<Chamado> buscarTodos() {

		List<Chamado> chamados = chamadoRepository.findAll();
		return chamados;

	}

	public Chamado salvar(ChamadoDTO chamadoDTO) {

		return chamadoRepository.save(newChamado(chamadoDTO));

	}
	
	public Chamado atualizar(Integer id, ChamadoDTO chamadoDTO) {
		chamadoDTO.setId(id);
		Chamado chamadoOld = buscarPorId(id);
		chamadoOld = newChamado(chamadoDTO);
		
		return chamadoRepository.save(chamadoOld);

	}

	

	private Chamado newChamado(ChamadoDTO chamadoDTO) {

		Tecnico tecnico = tenicoService.buscarPorId(chamadoDTO.getTecnico());
		Cliente cliente = clienteService.buscarPorId(chamadoDTO.getCliente());

		Chamado chamado = new Chamado();
		if (chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		 if (chamadoDTO.getStatus().equals(2)) {
			 chamado.setDataFechamanento(LocalDate.now());
		 }
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());
		return chamado;

	}

}
