package com.floppylab.salgotarjanschedule.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@ControllerAdvice(basePackages = "com.floppylab")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final static String ERROR_HAPPENED = "Error happened: {}";

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex) {
        return new ResponseEntity<>(createApiError(ex), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(Exception ex) {
        return new ResponseEntity<>(createApiError(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<Object> handleItemNotFoundException(Exception ex) {
        return new ResponseEntity<>(createApiError(ex), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        ApiError apiError = new ApiError(new Date(), "error.maxUploadSizeExceeded");
        log.error(ERROR_HAPPENED, ex);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(createApiError(ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ApiError createApiError(Exception ex) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage());
        log.error(ERROR_HAPPENED, ex);
        return apiError;
    }

}
