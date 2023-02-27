package com.charter.converter;

import com.charter.entity.TransactionEntity;
import com.charter.record.Transaction;
import com.charter.service.RewardTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RewardRequestConverterTest {

    private RewardRequestConverter converter = null;

    @BeforeEach
    void setUp() {
        converter = new RewardRequestConverter();
    }

    @Test
    void testConvert() {
        Transaction transaction = RewardTestUtil.createTransaction(200, 1, "2023-02-22");
        TransactionEntity actualTransactionEntity = converter.convert(transaction);
        assertNotNull(actualTransactionEntity);
        assertEquals(1, actualTransactionEntity.getCustomerId());
        assertEquals(transaction.amount(), actualTransactionEntity.getTotalAmount());
        assertEquals(transaction.purchaseDate(), actualTransactionEntity.getPurchaseDate().toLocalDate());

    }
}