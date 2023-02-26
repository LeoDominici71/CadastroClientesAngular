package com.LeoDominici71.CadastroCliente.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.LeoDominici71.CadastroCliente.rest.exception.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handlerValidationErrors(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> messages = bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
		return new ApiErrors(messages);
		
	}
	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
		String mensagemErro = ex.getMessage();
		HttpStatusCode codigoStatus = ex.getStatusCode();
		ApiErrors apiErrors = new ApiErrors(mensagemErro);
		return new ResponseEntity(apiErrors, codigoStatus);
		
	}

}