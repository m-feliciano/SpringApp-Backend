package com.feliciano.store.resources.exceptions;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.feliciano.store.services.exceptions.AuthorizationException;
import com.feliciano.store.services.exceptions.DataIntegrityException;
import com.feliciano.store.services.exceptions.FileException;
import com.feliciano.store.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotfound(ObjectNotFoundException e, HttpServletRequest request) {
        log.error("ResourceExceptionHandler - objectNotfound: {}", e.getMessage());

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Not found",
                null, request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        log.error("ResourceExceptionHandler - dataIntegrity: {}", e.getMessage());

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Data Integrity",
                null,
                request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> unprocessableEntity(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("ResourceExceptionHandler - unprocessableEntity: {}", e.getMessage());
        ValidationError error = new ValidationError(
                System.currentTimeMillis(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error",
                null,
                request.getRequestURI());

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        fieldErrors.forEach(f -> error.addError(f.getField(), f.getDefaultMessage()));

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

    @ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
        log.error("ResourceExceptionHandler - authorization: {}", e.getMessage());

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.FORBIDDEN.value(),
                "Denied access",
                null,
                request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {
        log.error("ResourceExceptionHandler - file: {}", e.getMessage());

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "File error",
                null,
                request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
        log.error("ResourceExceptionHandler - amazonService: {}", e.getMessage());

		HttpStatus code = HttpStatus.valueOf(e.getStatusCode());
        StandardError error = new StandardError(
                System.currentTimeMillis(),
                code.value(),
                "Error Amazon Service",
                null,
                request.getRequestURI());

		return ResponseEntity.status(code.value()).body(error);
	}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
        log.error("ResourceExceptionHandler - amazonClient: {}", e.getMessage());

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Error Amazon Client",
                null,
                request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {
        log.error("ResourceExceptionHandler - amazonS3: {}", e.getMessage());

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Error AmazonS3"
                , null,
                request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}