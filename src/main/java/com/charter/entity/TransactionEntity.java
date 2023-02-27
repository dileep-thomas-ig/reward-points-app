package com.charter.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionEntity {
    private long id;
    private int customerId;
    private BigDecimal totalAmount;
    private LocalDateTime purchaseDate;

    public TransactionEntity() {
    }

    public TransactionEntity(int customerId, BigDecimal totalAmount, LocalDateTime purchaseDate) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
