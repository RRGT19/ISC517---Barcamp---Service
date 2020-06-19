package com.example.PracticaDeDockerService.exception;

import org.omg.CORBA.portable.UnknownException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler({
          UnknownError.class,
          ResourceExistsException.class,
          ResourceNotFoundException.class,
          ResourceLengthException.class,
  })
  public ResponseEntity<ErrorDto> exception(Exception e, WebRequest request) {
    ErrorDto err;
    if (e instanceof ResourceExistsException) {
      err = handleResourceExistsException((ResourceExistsException) e, request);
    } else if (e instanceof ResourceNotFoundException) {
      err = handleResourceNotFoundException((ResourceNotFoundException) e, request);
    } else if (e instanceof ResourceLengthException) {
      err = handleResourceLengthException((ResourceLengthException) e, request);
    } else {
      err = handleUnknownErrorException((UnknownException) e, request);
    }
    return new ResponseEntity<>(err, err.getStatus());
  }

  private ErrorDto handleResourceExistsException(ResourceExistsException e, WebRequest request) {
    return new ErrorDto(HttpStatus.CONFLICT, ServerStatusMessages.RESOURCE_ALREADY_EXISTS, e.getMessage());
  }

  private ErrorDto handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
    return new ErrorDto(HttpStatus.NOT_FOUND, ServerStatusMessages.RESOURCE_NOT_FOUND, e.getMessage());
  }

  private ErrorDto handleResourceLengthException(ResourceLengthException e, WebRequest request) {
    return new ErrorDto(HttpStatus.NOT_FOUND, ServerStatusMessages.RESOURCE_BAD_LENGTH, e.getMessage());
  }

  private ErrorDto handleUnknownErrorException(UnknownException e, WebRequest request) {
    return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ServerStatusMessages.UNKNOWN_ERROR, e.getMessage());
  }

}
