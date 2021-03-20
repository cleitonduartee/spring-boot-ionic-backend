package com.cursomc.resource.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void setError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
