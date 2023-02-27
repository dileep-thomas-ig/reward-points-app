package com.charter.exception;

import com.charter.error.RewardApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RewardsRestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RewardsRestExceptionHandler.class);

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @return the ApiError object
     */
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {

        String error = ex.getParameterName() + " parameter is missing";
        RewardApiError rewardApiError = new RewardApiError(BAD_REQUEST, LocalDateTime.now(), error, ex.getLocalizedMessage());
        return buildResponseEntity(rewardApiError);
    }


    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @return the ApiError object
     */
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        RewardApiError rewardApiError = new RewardApiError(UNSUPPORTED_MEDIA_TYPE, LocalDateTime.now(),
                builder.substring(0, builder.length() - 2), ex.getLocalizedMessage());
        return buildResponseEntity(rewardApiError);

    }


    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param request WebRequest
     * @return the ApiError object
     */
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        LOGGER.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Malformed JSON request";
        RewardApiError rewardApiError = new RewardApiError(BAD_REQUEST, LocalDateTime.now(),
                error, ex.getLocalizedMessage());
        return buildResponseEntity(rewardApiError);
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @return the ApiError object
     */
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex) {
        String error = "Error writing JSON output";
        RewardApiError rewardApiError = new RewardApiError(INTERNAL_SERVER_ERROR, LocalDateTime.now(),
                error, ex.getLocalizedMessage());
        return buildResponseEntity(rewardApiError);
    }

    /**
     * Handle NoHandlerFoundException exception
     *
     * @param ex  NoHandlerFoundException
     * @return ResponseEntity<Object>
     */
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {

        RewardApiError rewardApiError = new RewardApiError(NOT_FOUND, LocalDateTime.now(),
                String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()),
                ex.getLocalizedMessage());
        return buildResponseEntity(rewardApiError);
    }

    /**
     * Handles all generic exceptions.
     *
     * @param exception Contains details about the exception.
     * @return ExceptionResponse includes appropriate exception details like error message,
     * http status code etc.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception exception) {
        RewardApiError rewardApiError = new RewardApiError(INTERNAL_SERVER_ERROR, LocalDateTime.now(),
                exception.getMessage(),
                exception.getLocalizedMessage());
        return buildResponseEntity(rewardApiError);
    }

    /**
     * Handles all RewardsNotFoundException exceptions.
     *
     * @param exception RewardsNotFoundException.
     * @return ExceptionResponse includes appropriate exception details like error message,
     * http status code etc.
     */
    @ExceptionHandler(RewardsNotFoundException.class)
    public ResponseEntity<Object> handleRewardsNotFoundException(RewardsNotFoundException exception) {
        RewardApiError rewardApiError = new RewardApiError(NOT_FOUND, LocalDateTime.now(),
                exception.getMessage(),
                exception.getLocalizedMessage());
        return buildResponseEntity(rewardApiError);
    }

    private ResponseEntity<Object> buildResponseEntity(RewardApiError rewardApiError) {
        return new ResponseEntity<>(rewardApiError, rewardApiError.status());
    }

}