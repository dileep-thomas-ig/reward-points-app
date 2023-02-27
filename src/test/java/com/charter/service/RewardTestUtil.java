package com.charter.service;

import com.charter.entity.TransactionEntity;
import com.charter.record.Reward;
import com.charter.record.RewardResponse;
import com.charter.record.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RewardTestUtil {

    public static List<TransactionEntity> mockTransactionEntity() {

        ArrayList<TransactionEntity> mockedTransactionEntity = new ArrayList<>();


        TransactionEntity transactionEntity = new TransactionEntity(1, BigDecimal.valueOf(120),
                getParsedDateTime("2023-02-22"));


        TransactionEntity transactionEntity1 = new TransactionEntity(1, BigDecimal.valueOf(130),
                getParsedDateTime("2023-01-22"));


        TransactionEntity transactionEntity2 = new TransactionEntity(1, BigDecimal.valueOf(140),
                getParsedDateTime("2022-12-22"));

        mockedTransactionEntity.add(transactionEntity);
        mockedTransactionEntity.add(transactionEntity1);
        mockedTransactionEntity.add(transactionEntity2);

        return mockedTransactionEntity;
    }

    private static LocalDate getParsedDate(String text) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(text, dateTimeFormatter);
    }

    private static LocalDateTime getParsedDateTime(String text) {
        return getParsedDate(text).atStartOfDay();
    }

    public static List<Reward> mockRewards() {

        ArrayList<Reward> mockedRewards = new ArrayList<>();
        Reward reward1 = new Reward(1, BigDecimal.valueOf(120),
                getParsedDate("2023-02-22"), 90);
        Reward reward2 = new Reward(1, BigDecimal.valueOf(130),
                getParsedDate("2023-01-22"), 110);
        Reward reward3 = new Reward(1, BigDecimal.valueOf(140),
                getParsedDate("2022-12-22"), 130);

        mockedRewards.add(reward1);
        mockedRewards.add(reward2);
        mockedRewards.add(reward3);
        return mockedRewards;
    }

    public static RewardResponse mockRewardResponse()  {
        return new RewardResponse(mockRewards(), 330);
    }

    public static TransactionEntity mockSingleTransactionEntity() {
        TransactionEntity entity = null;
        entity = new TransactionEntity(1, BigDecimal.valueOf(120),
                getParsedDateTime("2023-02-22"));
        return entity;
    }

    public static Transaction createTransaction(int amount, int customerID, String date)  {
        Transaction transaction = new Transaction(customerID, BigDecimal.valueOf(amount),
                getParsedDate(date));
        return transaction;

    }
}
