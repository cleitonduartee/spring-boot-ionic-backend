package com.cursomc.resource.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cursomc.services.exception.DataIntegrityException;
import com.cursomc.services.exception.ResourceNotFoundExeception;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundExeception.class)
	public ResponseEntity<StandarError> resourceNotFound (ResourceNotFoundExeception e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "Id não encontrado";
		StandarError err = new StandarError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandarError> dataIntegrity (DataIntegrityException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Erro de Integridade no Banco de Dados";
		StandarError err = new StandarError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandarError> validation (MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Erro de validação";
		ValidationError err = new ValidationError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.getErrors().add(new FieldMessage(x.getField(), x.getDefaultMessage()));
		}
		return ResponseEntity.status(status).body(err);
	}
}
