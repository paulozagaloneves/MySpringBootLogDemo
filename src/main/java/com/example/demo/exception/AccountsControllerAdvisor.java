package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.ApplicationMetadataConst;
import com.example.demo.utils.JsonUtils;

@ControllerAdvice
public class AccountsControllerAdvisor extends ResponseEntityExceptionHandler {
	private static Logger log  = LoggerFactory.getLogger(AccountsControllerAdvisor.class);
	

	@ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(
    		AccountNotFoundException ex, WebRequest request) {
		
		ResponseError error = new ResponseError();
		error.setApiName(ApplicationMetadataConst.API_NAME);
		error.setVersion(ApplicationMetadataConst.API_VERSION);
		error.setMessageId(UUID.randomUUID().toString());
		error.setCorrelationId(UUID.randomUUID().toString());
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode("404");
		error.setErrorType("APP_BUSINESS");
		error.setErrorSubType(AccountNotFoundException.SUB_TYPE);
		error.setErrorMessage(ex.getMessage());
		

		log.error("Account Not Found: \n{}", JsonUtils.toJsonSafe(error));
		log.error("Account Not Found: ", ex);
		
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(InvalidTypeException.class)
    public ResponseEntity<Object> handleInvalidTypeException(
    		InvalidTypeException ex, WebRequest request) {
		
		ResponseError error = new ResponseError();
		error.setApiName(ApplicationMetadataConst.API_NAME);
		error.setVersion(ApplicationMetadataConst.API_VERSION);
		error.setMessageId(UUID.randomUUID().toString());
		error.setCorrelationId(UUID.randomUUID().toString());
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode("400");
		error.setErrorType("APP_BUSINESS");
		error.setErrorSubType(InvalidTypeException.SUB_TYPE);
		error.setErrorMessage(ex.getMessage());
		

		log.error("InvalidTypeException: \n{}", JsonUtils.toJsonSafe(error));
		log.error("InvalidTypeException: ", ex);
		
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(
    		Exception ex, WebRequest request) {
		
		ResponseError error = new ResponseError();
		error.setApiName(ApplicationMetadataConst.API_NAME);
		error.setVersion(ApplicationMetadataConst.API_VERSION);
		error.setMessageId(UUID.randomUUID().toString());
		error.setCorrelationId(UUID.randomUUID().toString());
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode("500");
		error.setErrorType("APP_INTERNAL");
		error.setErrorSubType("ACCOUNT_API:GENERIC_ERROR");
		error.setErrorMessage(ex.getMessage());
		
		log.error("Generic Error: \n{}", JsonUtils.toJsonSafe(error));
		log.error("Generic Error: ", ex);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
