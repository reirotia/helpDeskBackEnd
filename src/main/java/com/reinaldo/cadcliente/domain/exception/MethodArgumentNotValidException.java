package com.reinaldo.cadcliente.domain.exception;

public class MethodArgumentNotValidException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public MethodArgumentNotValidException(String mensagem) {
		super(mensagem);
	}
}
