package com.charter.converter;

import com.charter.entity.TransactionEntity;
import com.charter.record.Transaction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * RewardRequestConverter converts model to entity.
 */
@Component
public class RewardRequestConverter implements Converter<Transaction, TransactionEntity> {

    /** converts type Transaction to TransactionEntity
     * @param source Transaction
     * @return TransactionEntity
     */
    @Override
    public TransactionEntity convert(Transaction source) {
        return new TransactionEntity(source.customerId(), source.amount(), source
                .purchaseDate()
                .atStartOfDay());
    }
}
