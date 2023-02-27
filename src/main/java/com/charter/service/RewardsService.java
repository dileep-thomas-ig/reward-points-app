package com.charter.service;

import com.charter.converter.RewardResponseConverter;
import com.charter.entity.TransactionEntity;
import com.charter.exception.RewardsNotFoundException;
import com.charter.record.RewardResponse;
import com.charter.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardsService {

    private static Logger LOGGER = LoggerFactory.getLogger(RewardsService.class);
    private final RewardResponseConverter converter;
    private final TransactionRepository transactionRepository;

    @Autowired
    public RewardsService(RewardResponseConverter converter, TransactionRepository transactionRepository) {
        this.converter = converter;
        this.transactionRepository = transactionRepository;
    }


    /**
     * calculate reward points for the given customerID
     *
     * @param customerID
     * @return RewardResponse
     */
    public RewardResponse calculateRewards(int customerID) {

        List<TransactionEntity> last3MonthRecordsByCustomerID = transactionRepository
                .findLast3MonthRecordsByCustomerID(customerID);

        if (hasNoRecords(last3MonthRecordsByCustomerID)) {
            LOGGER.error("No records found for the customer, with customer id {}", customerID);
            throw new RewardsNotFoundException(RewardsService.class, "CustomerID:", String.valueOf(customerID));
        }
        return converter.convert(last3MonthRecordsByCustomerID);
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

    /**
     * A method to check if records are present
     * @param last3MonthRecordsByCustomerID
     * @return boolean
     */
    private boolean hasNoRecords(List<TransactionEntity> last3MonthRecordsByCustomerID) {
        return last3MonthRecordsByCustomerID == null || last3MonthRecordsByCustomerID.size() == 0;
    }


}