package com.charter.integration;

import com.charter.RewardsConfig;
import com.charter.controller.RewardsController;
import com.charter.controller.TransactionController;
import com.charter.exception.RewardsNotFoundException;
import com.charter.record.Reward;
import com.charter.record.RewardResponse;
import com.charter.service.RewardTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RewardsConfig.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql({"classpath:init-script.sql"})
public class RewardsControllerIT {

    @Autowired
    private RewardsController rewardsController;
    @Autowired
    private TransactionController transactionController;

    @Test
    public void testFetchRewardPoints() {

        //when
        transactionController.create(RewardTestUtil.createTransaction(200, 17, "2023-02-22"));
        transactionController.create(RewardTestUtil.createTransaction(300, 17, "2023-01-22"));
        transactionController.create(RewardTestUtil.createTransaction(400, 17, "2022-12-22"));

        RewardResponse rewardResponse = rewardsController.fetchRewardPoints(17);


        //then
        assertNotNull(rewardResponse);
        List<Reward> actualRewards = rewardResponse.rewards();
        assertNotNull(actualRewards);
        assertEquals(3, actualRewards.size());
        List<Integer> allRewardPoints = actualRewards.stream()
                .map(Reward::rewardPoints)
                .collect(Collectors.toList());
        assertThat(allRewardPoints, contains(450, 250, 650));
        assertEquals(1350, rewardResponse.totalRewardPoints());
    }


    @Test
    public void testNoFetchRewardPoints() {

        //when
        RewardsNotFoundException rewardsNotFoundException = assertThrows(
                RewardsNotFoundException.class,
                () -> rewardsController.fetchRewardPoints(20),
                "RewardsService was not found for parameters {CustomerID:=20}");

        //then
        assertTrue(rewardsNotFoundException.getMessage()
                .contentEquals("RewardsService was not found for parameters {CustomerID:=20}"));

    }

}
