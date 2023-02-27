package com.charter.exception;

import com.charter.error.RewardApiError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class RewardsRestExceptionHandlerTest {

    private RewardsRestExceptionHandler exceptionResolver = null;

    private MockHttpServletRequest request = null;

    private MockHttpServletResponse response = null;

    ServletWebRequest servletWebRequest;

    @BeforeEach
    void setUp() {
        exceptionResolver = new RewardsRestExceptionHandler();
        request = new MockHttpServletRequest("GET", "/");
        response = new MockHttpServletResponse();
        servletWebRequest = new ServletWebRequest(request);
    }

    @Test
    void testHandleMissingServletRequestParameter() {
        //given
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("foo", "bar");
        //when
        ResponseEntity<Object> responseEntity = exceptionResolver.handleMissingServletRequestParameter(ex);
        //then
        RewardApiError body = (RewardApiError) responseEntity.getBody();
        assertThat(body.status()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testHandleHttpMediaTypeNotSupported() {
        //given
        HttpMediaTypeNotSupportedException ex = new HttpMediaTypeNotSupportedException(new MediaType("text", "plain"),
                Collections.singletonList(new MediaType("application", "pdf")));

        //when
        ResponseEntity<Object> responseEntity = exceptionResolver.handleHttpMediaTypeNotSupported(ex);
        //then
        RewardApiError body = (RewardApiError) responseEntity.getBody();
        assertThat(body.status()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }


    @Test
    void testHandleHttpMessageNotReadable() {
        //given
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("foo");

        //when

        ResponseEntity<Object> responseEntity = exceptionResolver.handleHttpMessageNotReadable(ex, servletWebRequest);

        //then
        RewardApiError body = (RewardApiError) responseEntity.getBody();
        assertThat(body.status()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testHandleHttpMessageNotWritable() {
        //given
        HttpMessageNotWritableException ex = new HttpMessageNotWritableException("foo");

        //when
        ResponseEntity<Object> responseEntity = exceptionResolver.handleHttpMessageNotWritable(ex);

        //then
        RewardApiError body = (RewardApiError) responseEntity.getBody();
        assertThat(body.status()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void testHandleNoHandlerFoundException() {
        //given
        ServletServerHttpRequest req = new ServletServerHttpRequest(
                new MockHttpServletRequest("GET", "/resource"));
        NoHandlerFoundException ex = new NoHandlerFoundException(req.getMethod().name(),
                req.getServletRequest().getRequestURI(), req.getHeaders());
        //when
        ResponseEntity<Object> responseEntity = exceptionResolver.handleNoHandlerFoundException(ex);

        //then
        RewardApiError body = (RewardApiError) responseEntity.getBody();
        assertThat(body.status()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testHandleGenericException() {

        //given
        RuntimeException exception = new RuntimeException("test any exception");
        //when
        ResponseEntity<Object> responseEntity = exceptionResolver.handleGenericException(exception);

        //then
        RewardApiError body = (RewardApiError) responseEntity.getBody();
        assertThat(body.status()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}