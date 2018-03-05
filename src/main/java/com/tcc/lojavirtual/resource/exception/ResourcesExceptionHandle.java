package com.tcc.lojavirtual.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.tcc.lojavirtual.service.exception.AuthorizationException;
import com.tcc.lojavirtual.service.exception.DataIntegrityException;
import com.tcc.lojavirtual.service.exception.FileException;
import com.tcc.lojavirtual.service.exception.NotFoundObjectException;



@Controller
@ControllerAdvice
public class ResourcesExceptionHandle {
	
	// Erro 422 -> Validação (Unprocessable Entity)
	
	// Retorna erros personalizados
	// Interceptam os erros HTTP capturando Exceptions especificados em @ExceptionHandler
	@ExceptionHandler(NotFoundObjectException.class)
	public ResponseEntity<StandardError> objectNotFound(NotFoundObjectException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(),  HttpStatus.NOT_FOUND.value(), "Não Encontrado",e.getMessage(), request.getRequestURI()); 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> DataIntegrity(DataIntegrityException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(),  HttpStatus.BAD_REQUEST.value(), "Integridade de dados",e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		ErrorValidation err = new ErrorValidation(System.currentTimeMillis(),  HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação",e.getMessage(), request.getRequestURI()); 
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(),  HttpStatus.FORBIDDEN.value(), "Acesso Negado",e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(),  HttpStatus.BAD_REQUEST.value(), "Erro de Arquivo",e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request){
		
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());		
		StandardError err = new StandardError(System.currentTimeMillis(),  code.value(), "Erro Amazon Service",e.getMessage(), request.getRequestURI()); 

		return ResponseEntity.status(code).body(err);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(),  HttpStatus.BAD_REQUEST.value(), "Erro Amazon Cliente",e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> AmazonS3(AmazonS3Exception e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(),  HttpStatus.BAD_REQUEST.value(), "Erro S3",e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<StandardError> multipart(MultipartException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(),  HttpStatus.BAD_REQUEST.value(), "Erro de Arquivo",e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
