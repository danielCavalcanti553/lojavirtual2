package com.tcc.lojavirtual.resource.exception;

import java.util.ArrayList;
import java.util.List;




public class ErrorValidation extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();


	public ErrorValidation(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return erros;
	}

	public void addError(String fileName, String message) {
		erros.add(new FieldMessage(fileName,message));
	}

	
}
