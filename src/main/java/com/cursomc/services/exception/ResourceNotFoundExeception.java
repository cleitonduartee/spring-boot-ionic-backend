package com.cursomc.services.exception;

public class ResourceNotFoundExeception extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundExeception(String msg) {
		super(msg);
	}

}
