package com.charter.controller;

import com.charter.converter.RewardRequestConverter;
import com.charter.record.Transaction;
import com.charter.service.RewardTestUtil;
import com.charter.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TransactionControllerTest {

    private TransactionController controller = null;
    private TransactionService service;
    private RewardRequestConverter converter;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(TransactionService.class);
        converter = Mockito.mock(RewardRequestConverter.class);
        controller = new TransactionController(service, converter);
    }

    @Test
    void testCreate()  {

        // given
        when(service.save(any())).thenReturn(1);
        when(converter.convert(any())).thenReturn(RewardTestUtil.mockSingleTransactionEntity());
        // when
        Transaction transaction = new Transaction(1, BigDecimal.valueOf(120), LocalDate.now());
        ResponseEntity<HttpStatus> httpStatusResponseEntity = controller.create(transaction);
        //then

        assertNotNull(httpStatusResponseEntity);
        assertEquals(HttpStatus.CREATED, httpStatusResponseEntity.getStatusCode());

    }
}