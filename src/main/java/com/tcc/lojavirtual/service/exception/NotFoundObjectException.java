package com.tcc.lojavirtual.service.exception;

public class NotFoundObjectException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NotFoundObjectException(String msg) {
		super(msg);
	}
	public NotFoundObjectException(String msg, Throwable cause) {
		super(msg);
	}	

}
