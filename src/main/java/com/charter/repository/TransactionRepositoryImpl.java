package com.charter.repository;

import com.charter.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String SELECT_ALL_RECORDS_FOR_LAST_3_MONTHS = "SELECT  purchase_date,  sum(amount) as totalAmount from transaction where purchase_date > now() - INTERVAL 3 MONTH and customer_id = ? group by Month(purchase_date)";

    private final String INSERT = "INSERT INTO transaction (customer_id, amount, purchase_date) VALUES(?,?,?)";

    @Autowired
    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This query fetches the last 3 months(from current date) of records and group by month
     * @param customerId
     * @return List<TransactionEntity>
     */
    @Override
    public List<TransactionEntity> findLast3MonthRecordsByCustomerID(int customerId) {
        return jdbcTemplate.query(SELECT_ALL_RECORDS_FOR_LAST_3_MONTHS, BeanPropertyRowMapper.newInstance(TransactionEntity.class), customerId);
    }


    /**
     * Saves the customer Transaction data.
     * @param transactionEntity
     * @return int
     */
    @Override
    public int save(TransactionEntity transactionEntity) {
        return jdbcTemplate.update(INSERT, new Object[]{transactionEntity.getCustomerId(),
                transactionEntity.getTotalAmount(), transactionEntity.getPurchaseDate()});
    }
}
