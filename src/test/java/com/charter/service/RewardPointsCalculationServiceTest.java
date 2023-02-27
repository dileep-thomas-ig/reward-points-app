package com.charter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RewardPointsCalculationServiceTest {

    private RewardPointsCalculationService service;

    @BeforeEach
    void setUp() {
        service = new RewardPointsCalculationService();
    }

    @Test()
    @DisplayName("Calculate Reward points with amount 50 ")
    void testCalculateRewardPointsWithAmountEqualTo50() {
        int actualRewardPoint = service.calculateRewardPoints(BigDecimal.valueOf(50));
        assertEquals(0, actualRewardPoint);
    }

    @Test()
    @DisplayName("Calculate Reward points with amount 51 ")
    void testCalculateRewardPointsWithAmountEqualTo51() {
        int actualRewardPoint = service.calculateRewardPoints(BigDecimal.valueOf(51));
        assertEquals(1, actualRewardPoint);
    }

    @Test()
    @DisplayName("Calculate Reward points with amount 100 ")
    void testCalculateRewardPointsWithAmountEqualTo100() {
        int actualRewardPoint = service.calculateRewardPoints(BigDecimal.valueOf(100));
        assertEquals(50, actualRewardPoint);
    }

    @Test()
    @DisplayName("Calculate Reward points with amount 120 ")
    void testCalculateRewardPointsWithAmountEqualTo120() {
        int actualRewardPoint = service.calculateRewardPoints(BigDecimal.valueOf(120));
        assertEquals(90, actualRewardPoint);
    }

    @Test()
    @DisplayName("Calculate Reward points with amount 500 ")
    void testCalculateRewardPointsWithAmountEqualTo500() {
        int actualRewardPoint = service.calculateRewardPoints(BigDecimal.valueOf(500));
        assertEquals(850, actualRewardPoint);
    }

    @Test()
    @DisplayName("Calculate Reward points with amount 120.50 ")
    void testCalculateRewardPointsWithAmountEqualTo12050() {
        int actualRewardPoint = service.calculateRewardPoints(BigDecimal.valueOf(120.50));
        assertEquals(91, actualRewardPoint);
    }

    @Test()
    @DisplayName("Calculate Reward points with amount 120.63 ")
    void testCalculateRewardPointsWithAmountEqualTo12063() {
        int actualRewardPoint = service.calculateRewardPoints(BigDecimal.valueOf(120.63));
        assertEquals(91, actualRewardPoint);
    }

    @Test()
    @DisplayName("Calculate Reward points with amount 50.23 ")
    void testCalculateRewardPointsWithAmountEqualTo5023() {
        int actualRewardPoint = service.calculateRewardPoints(BigDecimal.valueOf(50.23));
        assertEquals(0, actualRewardPoint);
    }
}