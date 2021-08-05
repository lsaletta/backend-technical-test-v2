package com.tui.proof.ws.handler;


import com.tui.proof.exception.TUIMMException;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler {@link TUIMMException}.
     *
     * @param ex
     * @param request
     * @return response with NO_CONTENT httpStatus
     */
    @ExceptionHandler(TUIMMException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ErrorMessage handlerTUIMM(final TUIMMException ex, final WebRequest request) {
        ErrorMessage message = new ErrorMessage(ex.getErrorDescription());
        return message;
    }

    /**
     * Handler {@link ConstraintViolationException}.
     *
     * @param ex
     * @param request
     * @return response with BAD_REQUEST httpStatus
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerErrorValidations(final ConstraintViolationException ex,
                                                final WebRequest request) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return message;
    }

    /**
     * Handler {@link MethodArgumentNotValidException}.
     *
     * @param ex
     * @param request
     * @return response with BAD_REQUEST httpStatus
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String fieldErrors = StringUtils.EMPTY;
        if (ex.hasErrors()) {
            List<FieldError> fieldErrorList = ex.getFieldErrors();
            fieldErrors = fieldErrorList.stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining(","));
        }
        return new ResponseEntity<>(new ErrorMessage(fieldErrors), HttpStatus.BAD_REQUEST);
    }
}
