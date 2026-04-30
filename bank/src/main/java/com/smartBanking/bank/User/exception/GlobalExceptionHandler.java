package com.smartBanking.bank.User.exception;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.smartBanking.bank.apiResponse.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleUserNotFoundException(UserNotFoundException e)
	{
		
		ApiResponse<?> response=new ApiResponse<>(
				e.getMessage(),
				null,
				false,
				HttpStatus.NOT_FOUND.value()
				);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleMethodArgNotValidException(MethodArgumentNotValidException ex)
	{
		String error=ex.getBindingResult()
				.getFieldError()
				.getDefaultMessage();
		
		ApiResponse<?> response=new ApiResponse<>(
			    error,
				null,
				false,
				HttpStatus.BAD_REQUEST.value()
				);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ApiResponse<?>> handleResourceAlreadyExitsException(ResourceAlreadyExistsException e)
	{
		
		ApiResponse<?> response=new ApiResponse<>(
				e.getMessage(),
				null,
				false,
				HttpStatus.CONFLICT.value()
				);
		return new ResponseEntity<>(response,HttpStatus.CONFLICT);
	}
}
