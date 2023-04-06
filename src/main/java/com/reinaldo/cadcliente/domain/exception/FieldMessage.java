package com.reinaldo.cadcliente.domain.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String fildMessage;

	public FieldMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FieldMessage(String fieldName, String fildMessage) {
		super();
		this.fieldName = fieldName;
		this.fildMessage = fildMessage;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFildMessage() {
		return fildMessage;
	}

	public void setFildMessage(String fildMessage) {
		this.fildMessage = fildMessage;
	}

}
