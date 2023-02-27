package com.charter.record;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Reward(int customerId, BigDecimal amount, LocalDate purchaseDate, int rewardPoints) {
}
