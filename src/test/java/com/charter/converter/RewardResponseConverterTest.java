package com.charter.converter;

import com.charter.entity.TransactionEntity;
import com.charter.record.Reward;
import com.charter.record.RewardResponse;
import com.charter.service.RewardPointsCalculationService;
import com.charter.service.RewardTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class RewardResponseConverterTest {

    private RewardResponseConverter converter;
    private RewardPointsCalculationService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(RewardPointsCalculationService.class);
        converter = new RewardResponseConverter(service);
    }

    @Test
    void testConvert()  {
        // Given
        List<TransactionEntity> expectedTransactionEntities = RewardTestUtil.mockTransactionEntity();
        when(service.calculateRewardPoints(BigDecimal.valueOf(120))).thenReturn(90);
        when(service.calculateRewardPoints(BigDecimal.valueOf(130))).thenReturn(110);
        when(service.calculateRewardPoints(BigDecimal.valueOf(140))).thenReturn(130);

        //when
        RewardResponse actualRewardResponse = converter.convert(expectedTransactionEntities);

        //then
        assertNotNull(actualRewardResponse);
        List<Reward> actualRewards = actualRewardResponse.rewards();
        assertNotNull(actualRewards);
        assertEquals(3, actualRewards.size());

    }
}