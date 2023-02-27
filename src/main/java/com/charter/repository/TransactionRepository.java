package com.charter.repository;

import com.charter.entity.TransactionEntity;

import java.util.List;

public interface TransactionRepository {

    List<TransactionEntity> findLast3MonthRecordsByCustomerID(int id);

    public int save(TransactionEntity transactionEntity);
}
