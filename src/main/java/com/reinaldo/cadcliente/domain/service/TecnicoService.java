package com.reinaldo.cadcliente.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinaldo.cadcliente.api.model.TecnicoDTO;
import com.reinaldo.cadcliente.domain.exception.DataIntegrityViolationException;
import com.reinaldo.cadcliente.domain.exception.EntidadeNaoEncontradaException;
import com.reinaldo.cadcliente.domain.model.Pessoa;
import com.reinaldo.cadcliente.domain.model.Tecnico;
import com.reinaldo.cadcliente.domain.repository.PessoaRepository;
import com.reinaldo.cadcliente.domain.repository.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepo;

	@Autowired
	private PessoaRepository pessoaRepo;
	


	public Tecnico buscarPorId(Integer id) {

		return tecnicoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(id));

	}

	public List<Tecnico> buscarTodos() {
		return tecnicoRepo.findAll();
	}

	@Transactional
	public Tecnico salvar(TecnicoDTO obj) {
		obj.setId(null);
		obj.setSenha(obj.getSenha());
		validaCPFEmail(obj);
		Tecnico tecnico = new Tecnico(obj);
		return tecnicoRepo.save(tecnico);
	}

	private void validaCPFEmail(TecnicoDTO obj) {

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
	public Tecnico atualizar(Integer id, TecnicoDTO obj) {
		obj.setId(id);
		Tecnico tecnicoOld = buscarPorId(id);
		validaCPFEmail(obj);
		tecnicoOld = new Tecnico(obj);
		return tecnicoRepo.save(tecnicoOld);
	}

	@Transactional
	public void delete(Integer id) {
		Tecnico tec = buscarPorId(id);
		if (tec.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possuichamado e não pode ser deletado ");
		}

		tecnicoRepo.deleteById(id);
	}
}
