package com.eugene.exception;

import com.eugene.common.MediaTypes;
import com.eugene.model.ResponseMsg;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Called when an exception occurs during request processing. Transforms exception message into JSON format.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ResponseMsg> handleGenericException(Exception ex, HttpServletRequest request) {
        logError(ex, request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
        ResponseMsg msg = new ResponseMsg(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(msg, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<ResponseMsg> handleCustomRestException(RestException ex, HttpServletRequest request) {
        logError(ex, request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
        return new ResponseEntity<>(new ResponseMsg(ex.errorCode.code, ex.getMessage()),
                headers, HttpStatus.valueOf(ex.errorCode.httpStatus));
    }

    /**
     * 重载ResponseEntityExceptionHandler的方法，加入日志
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(ex);

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }


    private void logError(Exception ex) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n*************************************************************\n")
                .append("message: " + ex.getMessage() + "\n")
                .append("*************************************************************\n");
        logger.error(builder.toString(), ex);
    }

    private void logError(Exception ex, HttpServletRequest request) {
        String queryString = request.getQueryString();
        String path = queryString != null ?
                (request.getRequestURI() + "?" + queryString) : request.getRequestURI();

        StringBuilder builder = new StringBuilder();
        builder.append("\n*************************************************************\n")
                .append("message: " + ex.getMessage() + "\n")
                .append("from: " + request.getRemoteAddr()  + "\n")
                .append("path" + path + "\n")
                .append("*************************************************************\n");

        logger.error(builder.toString(), ex);
    }

}
