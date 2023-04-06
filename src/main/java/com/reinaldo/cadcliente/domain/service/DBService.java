package com.reinaldo.cadcliente.domain.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinaldo.cadcliente.domain.enums.Perfil;
import com.reinaldo.cadcliente.domain.enums.Prioridade;
import com.reinaldo.cadcliente.domain.enums.Status;
import com.reinaldo.cadcliente.domain.model.Chamado;
import com.reinaldo.cadcliente.domain.model.Cliente;
import com.reinaldo.cadcliente.domain.model.Tecnico;
import com.reinaldo.cadcliente.domain.repository.ChamadoRepository;
import com.reinaldo.cadcliente.domain.repository.ClienteRepository;
import com.reinaldo.cadcliente.domain.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	

	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Reinaldo", "370.629.970-48", "reirotia@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);		
		Cliente cli1 = new Cliente(null, "Caixa", "396.903.560-03", "reirotia1@gmail.com", "123");		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamdo 01", "Primeiro chamdo", 
				tec1, cli1);
		
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
		
	}
}
