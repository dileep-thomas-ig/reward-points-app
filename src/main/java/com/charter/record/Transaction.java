package com.charter.record;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(int customerId, BigDecimal amount, LocalDate purchaseDate) {
}