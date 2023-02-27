package com.charter.controller;

import com.charter.exception.RewardsNotFoundException;
import com.charter.record.RewardResponse;
import com.charter.service.RewardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rewards")
public class RewardsController {
    private static Logger LOGGER = LoggerFactory.getLogger(RewardsController.class);
    private final RewardsService rewardsService;


    @Autowired
    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    /**
     * fetchRewardPoints returns reward points for the customer
     * for last 3 months and also total reward points.
     *
     * @param customerID
     * @return RewardResponse
     * @throws RewardsNotFoundException
     */
    @GetMapping("/points/{customerID}")
    public RewardResponse fetchRewardPoints(@PathVariable("customerID") int customerID) throws RewardsNotFoundException {
        LOGGER.info("Logged in customer Id: {} ", customerID);
        return rewardsService.calculateRewards(customerID);
    }

}
