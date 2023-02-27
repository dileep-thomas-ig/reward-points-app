package com.charter.controller;

import com.charter.converter.RewardRequestConverter;
import com.charter.exception.RewardsNotFoundException;
import com.charter.record.Reward;
import com.charter.record.RewardResponse;
import com.charter.service.RewardTestUtil;
import com.charter.service.RewardsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class RewardsControllerTest {

    private RewardsController controller = null;
    private RewardsService rewardsService = null;
    private RewardRequestConverter converter = null;

    @BeforeEach
    void setUp() {
        rewardsService = Mockito.mock(RewardsService.class);
        converter = Mockito.mock(RewardRequestConverter.class);
        controller = new RewardsController(rewardsService);
    }

    @Test
    void testFetchRewardPoints() {
        //given
        RewardResponse mockRewardResponse = mockRewardResponse();
        when(converter.convert(any())).thenReturn(RewardTestUtil.mockSingleTransactionEntity());
        when(rewardsService.calculateRewards(anyInt())).thenReturn(mockRewardResponse);

        //when
        RewardResponse actualRewardResponse = controller.fetchRewardPoints(1);

        //then
        assertNotNull(actualRewardResponse);
        List<Reward> actualRewards = actualRewardResponse.rewards();
        assertNotNull(actualRewards);
        assertEquals(3, actualRewards.size());
        assertIterableEquals(mockRewardResponse.rewards(), actualRewards);
    }

    @Test
    void testFetchRewardPointsWithRewardsNotFoundException() {
        //given
        List<Reward> expectedRewards = RewardTestUtil.mockRewards();
        when(rewardsService.calculateRewards(anyInt()))
                .thenThrow(new RewardsNotFoundException(RewardsService.class, "CustomerID:", "1"));

        //when
        RewardsNotFoundException rewardsNotFoundException = assertThrows(
                RewardsNotFoundException.class,
                () -> controller.fetchRewardPoints(1),
                "RewardsService was not found for parameters {CustomerID:=1}");

        //then
        assertTrue(rewardsNotFoundException.getMessage()
                .contentEquals("RewardsService was not found for parameters {CustomerID:=1}"));
    }


    private RewardResponse mockRewardResponse() {
        return RewardTestUtil.mockRewardResponse();
    }


}