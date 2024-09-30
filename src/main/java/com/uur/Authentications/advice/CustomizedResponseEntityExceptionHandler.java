package com.uur.Authentications.advice;

import com.uur.Authentications.dtos.ErrorDto;
import com.uur.Authentications.exceptions.BadRequestException;
import com.uur.Authentications.exceptions.ForbiddenException;
import com.uur.Authentications.exceptions.NotFoundException;
import com.uur.Authentications.exceptions.UnAuthorizeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ErrorDto> handleAuthenticationExceptionn(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDto>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public final ResponseEntity<ErrorDto> handleInsufficientAuthenticationException(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDto>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDto> handleNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDto>(errorDetails, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorDto> handleBadRequestException(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDto>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorDto> handleIllegalArgumentException(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDto>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<ErrorDto> handleForbiddenException(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDto>(errorDetails, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(UnAuthorizeException.class)
    public final ResponseEntity<ErrorDto> handleUnAuthorizeException(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDto>(errorDetails, HttpStatus.UNAUTHORIZED);
    }



    @Override
    protected ResponseEntity<java.lang.Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorDto> errors = ex.getBindingResult().getFieldErrors()
                .stream().map( s-> new ErrorDto(LocalDateTime.now(), s.getDefaultMessage(), s.getField()) ).collect(Collectors.toList());
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDto> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDto>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
