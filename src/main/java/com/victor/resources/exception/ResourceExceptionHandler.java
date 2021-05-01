package com.victor.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.victor.services.exceptions.ObjectNotFoundException;
/**
 * Classe responsável por possuir métodos para retornar mensagens de erro de REST.
 * 
 * @author Victor 
 *
 */
@ControllerAdvice
public class ResourceExceptionHandler {
	
	/**
	 * Método responsável por retornar as mensagens de erro caso o objeto solicitado não seja encontrado.
	 * @param e
	 * @param request
	 * @return
	 */
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request)
	{
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
