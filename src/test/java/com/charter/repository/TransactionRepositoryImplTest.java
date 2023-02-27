package com.charter.repository;

import com.charter.entity.TransactionEntity;
import com.charter.record.Reward;
import com.charter.service.RewardTestUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TransactionRepositoryImplTest {

    private static final String SELECT_LAST_3_MONTHS = "SELECT  purchase_date,  sum(amount) as totalAmount from rewards " +
            "where purchase_date > now() - INTERVAL 3 MONTH and customer_id = ? group by Month(purchase_date)";
    private JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);

    @Test
    void testFindLast3MonthRecordsByCustomerID()  {
        // given
        List<TransactionEntity> mockedTransactionEntity = mockTransactionEntity();
        when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(BeanPropertyRowMapper.class), any())).thenReturn(mockedTransactionEntity);
        jdbcTemplate.query(SELECT_LAST_3_MONTHS, BeanPropertyRowMapper.newInstance(TransactionEntity.class), 1);

        // when
        List<TransactionEntity> actualTransactionEntityList = jdbcTemplate.query(SELECT_LAST_3_MONTHS, BeanPropertyRowMapper.newInstance(TransactionEntity.class), 1);

        //then
        assertNotNull(actualTransactionEntityList);
        TransactionEntity actualTransactionEntity = actualTransactionEntityList.get(0);
        assertNotNull(actualTransactionEntity);
        assertEquals(mockedTransactionEntity.get(0).getTotalAmount(), actualTransactionEntity.getTotalAmount());
        assertEquals(mockedTransactionEntity.get(0).getCustomerId(), actualTransactionEntity.getCustomerId());
        assertEquals(mockedTransactionEntity.get(0).getPurchaseDate(), actualTransactionEntity.getPurchaseDate());

    }


    @Test
    void testFindLast3MonthRecordsByCustomerIDWithNoRecords() {
        // given
        when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(BeanPropertyRowMapper.class), any())).thenReturn(Collections.EMPTY_LIST);
        jdbcTemplate.query(SELECT_LAST_3_MONTHS, BeanPropertyRowMapper.newInstance(Reward.class), 1);

        // when
        List<Reward> actualRewards = jdbcTemplate.query(SELECT_LAST_3_MONTHS, BeanPropertyRowMapper.newInstance(Reward.class), 1);

        //then
        assertEquals(0, actualRewards.size());

    }

    private List<TransactionEntity> mockTransactionEntity() {
        TransactionEntity build = RewardTestUtil.mockSingleTransactionEntity();
        return Arrays.asList(build);
    }

}