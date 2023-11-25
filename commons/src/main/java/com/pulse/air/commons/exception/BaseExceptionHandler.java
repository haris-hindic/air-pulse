package com.pulse.air.commons.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ErrorApiResponse;

public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {

		var errors = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

		return new ResponseEntity<>(new ErrorApiResponse(HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getLocalizedMessage(), errors), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorApiResponse> handleCustomException(final ApiException ex) {

		var errorResponse = new ErrorApiResponse(ex.getStatus().value(), ex.getStatus().getReasonPhrase(),
				ex.getLocalizedMessage(), null);
		ex.printStackTrace();
		return new ResponseEntity<>(errorResponse, ex.getStatus());

	}
}
