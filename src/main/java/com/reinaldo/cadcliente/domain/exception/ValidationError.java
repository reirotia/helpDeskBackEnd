package com.reinaldo.cadcliente.domain.exception;

import java.util.ArrayList;
import java.util.List;

import com.reinaldo.cadcliente.api.exceptionHandler.StandardError;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timeStamp, Integer status, String error, String message, String path) {
		super(timeStamp, status, error, message, path);
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String fieldName, String message) {
		this.erros.add(new FieldMessage(fieldName, message));
	}
	
	
	

}
