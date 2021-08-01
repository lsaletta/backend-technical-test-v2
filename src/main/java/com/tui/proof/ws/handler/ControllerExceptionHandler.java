package com.tui.proof.ws.handler;


import com.tui.proof.exception.TUIMMException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handler {@link TUIMMException}.
     * @param ex
     * @param request
     * @return response with NO_CONTENT httpStatus
     */
    @ExceptionHandler(TUIMMException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ErrorMessage handler(final TUIMMException ex, final WebRequest request) {
        ErrorMessage message = new ErrorMessage(ex.getErrorDescription());
        return message;
    }
}
