package com.charter.service;

import com.charter.entity.TransactionEntity;
import com.charter.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    /**
     * A method to save reward point date along with amount
     * spent by user
     *
     * @param transactionEntity
     * @return int
     */

    public int save(TransactionEntity transactionEntity) {
        return transactionRepository.save(transactionEntity);
    }




}