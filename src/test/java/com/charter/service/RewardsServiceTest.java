package com.charter.service;

import com.charter.converter.RewardResponseConverter;
import com.charter.entity.TransactionEntity;
import com.charter.exception.RewardsNotFoundException;
import com.charter.record.Reward;
import com.charter.record.RewardResponse;
import com.charter.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class RewardsServiceTest {

    private TransactionRepository transactionRepository = null;
    private RewardResponseConverter rewardResponseConverter = null;
    private RewardsService rewardsService = null;

    @BeforeEach
    void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        rewardResponseConverter = Mockito.mock(RewardResponseConverter.class);
        rewardsService = new RewardsService(rewardResponseConverter, transactionRepository);
    }

    @Test
    void testCalculateRewards() {
        //given
        List<TransactionEntity> expectedTransactionEntity = RewardTestUtil.mockTransactionEntity();
        when(rewardResponseConverter.convert(any())).thenReturn(RewardTestUtil.mockRewardResponse());
        when(transactionRepository.findLast3MonthRecordsByCustomerID(anyInt())).thenReturn(expectedTransactionEntity);

        //when
        RewardResponse actualRewardResponse = rewardsService.calculateRewards(1);

        //then
        assertNotNull(actualRewardResponse);
        List<Reward> actualRewards = actualRewardResponse.rewards();
        assertNotNull(actualRewards);
        assertEquals(3, actualRewards.size());
    }


    @Test()
    void testCalculateRewardsWithRewardsNotFoundException() {
        //given
        when(transactionRepository.findLast3MonthRecordsByCustomerID(anyInt())).thenReturn(Collections.EMPTY_LIST);

        //when
        RewardsNotFoundException rewardsNotFoundException = assertThrows(
                RewardsNotFoundException.class,
                () -> rewardsService.calculateRewards(1),
                "RewardsService was not found for parameters {CustomerID:=1}");

        //then
        assertTrue(rewardsNotFoundException.getMessage()
                .contentEquals("RewardsService was not found for parameters {CustomerID:=1}"));
    }


}