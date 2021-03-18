package com.cursomc.resource.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
