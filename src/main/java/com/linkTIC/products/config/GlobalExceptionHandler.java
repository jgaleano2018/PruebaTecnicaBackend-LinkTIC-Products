package com.linkTIC.products.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.linkTIC.products.application.service.EntityDuplicateException;
import com.linkTIC.products.application.service.EntityNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
  private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  record ErrorResponse(Instant timestamp, int status, String error, String message, String path) {}

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(EntityNotFoundException ex, HttpServletRequest req) {
    log.info("Not found: {} {}", req.getMethod(), req.getRequestURI());
    return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), req.getRequestURI());
  }

  @ExceptionHandler(EntityDuplicateException.class)
  public ResponseEntity<ErrorResponse> handleDuplicate(EntityDuplicateException ex, HttpServletRequest req) {
    log.warn("Duplicate: {} {}", req.getMethod(), req.getRequestURI());
    return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), req.getRequestURI());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
    String message = ex.getBindingResult().getFieldErrors().stream()
        .map(e -> e.getField() + ": " + e.getDefaultMessage())
        .collect(Collectors.joining("; "));
    log.debug("Validation failed: {}", message);
    return buildResponse(HttpStatus.BAD_REQUEST, message, req.getRequestURI());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDBIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
    log.error("DB integrity violation", ex);
    return buildResponse(HttpStatus.BAD_REQUEST, "Database constraint violated", req.getRequestURI());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAll(Exception ex, HttpServletRequest req) {
    log.error("Unhandled error while processing request", ex);
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", req.getRequestURI());
  }

  private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, String path) {
    ErrorResponse body = new ErrorResponse(Instant.now(), status.value(), status.getReasonPhrase(), message, path);
    return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(body);
  }
}