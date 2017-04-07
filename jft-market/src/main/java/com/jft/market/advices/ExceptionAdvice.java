package com.jft.market.advices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jft.market.api.ws.ErrorWS;
import com.jft.market.api.ws.PaymentResponseWS;
import com.jft.market.bindings.ErrorResource;
import com.jft.market.bindings.FieldErrorResource;
import com.jft.market.exceptions.EntityAlreadyExist;
import com.jft.market.exceptions.EntityNotFoundException;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.exceptions.InternalApiException;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.exceptions.VantivPaymnetException;

@RestControllerAdvice
@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {


	private HttpHeaders creatHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	@ExceptionHandler({InvalidRequestException.class})
	protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
		InvalidRequestException invalidRequestException = (InvalidRequestException) e;
		List<FieldErrorResource> fieldErrorResources = new ArrayList<>();
		ErrorResource errorResource = new ErrorResource(ExceptionConstants.INVALID_REQUEST);
		List<FieldError> fieldErrors = invalidRequestException.getErrors().getFieldErrors();
		fieldErrors.forEach(fieldError -> {
			FieldErrorResource fieldErrorResource = new FieldErrorResource();
			fieldErrorResource.setCode(fieldError.getCode());
			fieldErrorResource.setField(fieldError.getField());
			fieldErrorResource.setMessage(fieldError.getDefaultMessage());
			fieldErrorResources.add(fieldErrorResource);
			errorResource.setFieldErrors(fieldErrorResources);
		});
		HttpHeaders headers = creatHeaders();
		return handleExceptionInternal(e, errorResource, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}

	@ExceptionHandler({EntityNotFoundException.class})
	protected ResponseEntity<Object> entityNotFound(RuntimeException e) {
		EntityNotFoundException entityNotFoundException = (EntityNotFoundException) e;
		return new ResponseEntity(new ErrorWS(entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({EntityAlreadyExist.class})
	protected ResponseEntity<Object> entityAlreadyExist(RuntimeException e) {
		return new ResponseEntity(new ErrorWS(e.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
	}

	@ExceptionHandler({InternalApiException.class})
	protected ResponseEntity<Object> handleInternalApiException(RuntimeException e) {
		return new ResponseEntity(new ErrorWS(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({VantivPaymnetException.class})
	protected ResponseEntity<Object> handlePaymentException(RuntimeException e) {
		return new ResponseEntity(new PaymentResponseWS(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
