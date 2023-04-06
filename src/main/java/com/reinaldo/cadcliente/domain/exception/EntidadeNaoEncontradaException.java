package com.reinaldo.cadcliente.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {
	
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public EntidadeNaoEncontradaException(Integer tecnicoId) {
		this(String.format("Não existe Entidade com código %d", tecnicoId));
	}
}
