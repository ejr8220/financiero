package com.bancopichincha.ec.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bancopichincha.ec.Dto.ErrorDto;
import com.bancopichincha.ec.utils.CustomException;

@RestControllerAdvice
public class AdviceController {
	
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<ErrorDto> CustomExceptionHandler(CustomException e){
		ErrorDto error = ErrorDto.builder().code(e.getCode()).message(e.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorDto> ExceptionHandler(Exception e){
		ErrorDto error = ErrorDto.builder().code("500").message(e.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
