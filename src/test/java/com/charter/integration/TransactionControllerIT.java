package com.charter.integration;

import com.charter.RewardsConfig;
import com.charter.controller.TransactionController;
import com.charter.service.RewardTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RewardsConfig.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql({"classpath:init-script.sql"})
public class TransactionControllerIT {

    @Autowired
    private TransactionController transactionController;

    @Test
    public void testCreate() {

        //when
        ResponseEntity<HttpStatus> httpStatusResponseEntity = transactionController.create(RewardTestUtil.createTransaction(200, 17, "2023-02-22"));
        assertNotNull(httpStatusResponseEntity);
        assertEquals(HttpStatus.CREATED, httpStatusResponseEntity.getStatusCode());

    }


}
