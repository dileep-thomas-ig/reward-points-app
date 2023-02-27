package com.charter.controller;

import com.charter.converter.RewardRequestConverter;
import com.charter.record.Transaction;
import com.charter.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService service;
    private final RewardRequestConverter converter;
    @Autowired
    public TransactionController(TransactionService service, RewardRequestConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    /**
     * Saves the reward amount, in order to calculate
     * reward points in fetchRewardPoints flow.
     * @param transaction
     * @return ResponseEntity
     */
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody Transaction transaction) {
        service.save(converter.convert(transaction));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
