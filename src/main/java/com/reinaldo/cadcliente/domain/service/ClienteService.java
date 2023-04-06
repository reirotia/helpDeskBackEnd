package com.reinaldo.cadcliente.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinaldo.cadcliente.api.model.ClienteDTO;
import com.reinaldo.cadcliente.domain.exception.DataIntegrityViolationException;
import com.reinaldo.cadcliente.domain.exception.EntidadeNaoEncontradaException;
import com.reinaldo.cadcliente.domain.model.Cliente;
import com.reinaldo.cadcliente.domain.model.Pessoa;
import com.reinaldo.cadcliente.domain.repository.ClienteRepository;
import com.reinaldo.cadcliente.domain.repository.PessoaRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private PessoaRepository pessoaRepo;
	


	public Cliente buscarPorId(Integer id) {

		return clienteRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(id));

	}

	public List<Cliente> buscarTodos() {
		return clienteRepo.findAll();
	}

	@Transactional
	public Cliente salvar(ClienteDTO obj) {
		obj.setId(null);
		obj.setSenha(obj.getSenha());
		validaCPFEmail(obj);
		Cliente Cliente = new Cliente(obj);
		return clienteRepo.save(Cliente);
	}

	private void validaCPFEmail(ClienteDTO obj) {

		Optional<Pessoa> pessoa = pessoaRepo.findByCpf(obj.getCpf());
		if (pessoa.isPresent() && pessoa.get().getId() != obj.getId()) {
			throw new DataIntegrityViolationException("CPF ja cadastrado");
		}

		pessoa = pessoaRepo.findByEmail(obj.getEmail());
		if (pessoa.isPresent() && pessoa.get().getId() != obj.getId()) {
			throw new DataIntegrityViolationException("Email ja cadastrado");
		}

	}

	@Transactional
	public Cliente atualizar(Integer id, ClienteDTO obj) {
		obj.setId(id);
		Cliente ClienteOld = buscarPorId(id);
		validaCPFEmail(obj);
		ClienteOld = new Cliente(obj);
		return clienteRepo.save(ClienteOld);
	}

	@Transactional
	public void delete(Integer id) {
		Cliente cli = buscarPorId(id);
		if (cli.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui chamado e não pode ser deletado ");
		}

		clienteRepo.deleteById(id);
	}
}
