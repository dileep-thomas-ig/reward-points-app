package com.charter.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A service to calculate reward points
 * based on the amount spent by user
 */
@Service
public class RewardPointsCalculationService {

    public static final int LOWER_BOUND = 50;
    public static final int UPPER_BOUND = 100;
    public static final int MULTIPLIER = 2;

    /**
     * calculateRewardPoints calculates reward points applicable
     * to the customer based on the amount
     * A customer receives 2 points for every dollar spent over $100 in each transaction,
     * plus 1 point for every dollar spent between $50 and $100 in each transaction.
     * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
     *
     * @param amount
     * @return int reward point
     */
    public int calculateRewardPoints(BigDecimal amount) {
        BigDecimal defaultValue = BigDecimal.ZERO;
        if (amount.compareTo(BigDecimal.valueOf(LOWER_BOUND)) > 0
                && amount.compareTo(BigDecimal.valueOf(UPPER_BOUND)) <= 0) {
            defaultValue = amount.subtract(BigDecimal.valueOf(LOWER_BOUND));
        } else if (amount.compareTo(BigDecimal.valueOf(UPPER_BOUND)) > 0) {
            defaultValue = amount.subtract(BigDecimal.valueOf(UPPER_BOUND))
                    .multiply(BigDecimal.valueOf(MULTIPLIER))
                    .add(BigDecimal.valueOf(LOWER_BOUND));
        }
        return defaultValue
                .setScale(0, RoundingMode.FLOOR)
                .intValue();
    }
}
