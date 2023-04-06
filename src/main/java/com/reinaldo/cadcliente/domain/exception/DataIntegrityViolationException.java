package com.reinaldo.cadcliente.domain.exception;

public class DataIntegrityViolationException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityViolationException(String mensagem) {
		super(mensagem);
	}
}
