package com.charter.converter;

import com.charter.entity.TransactionEntity;
import com.charter.record.Reward;
import com.charter.record.RewardResponse;
import com.charter.service.RewardPointsCalculationService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * RewardResponseConverter converts List<TransactionEntity to model response.
 */
@Component
public class RewardResponseConverter implements Converter<List<TransactionEntity>, RewardResponse> {

    private final RewardPointsCalculationService rewardPointsCalculationService;

    public RewardResponseConverter(RewardPointsCalculationService rewardPointsCalculationService) {
        this.rewardPointsCalculationService = rewardPointsCalculationService;
    }

    /**
     * A method to convert entity to domain type
     * @param source of type List<TransactionEntity>
     * @return RewardResponse
     */
    @Override
    public RewardResponse convert(List<TransactionEntity> source) {
        List<Reward> rewards = source.stream()
                .map(this::calculateRewardPointsInternal)
                .collect(Collectors.toList());
        Integer totalRewardPoints = rewards.stream()
                .map(point -> point.rewardPoints())
                .reduce(0, Integer::sum);
        return new RewardResponse(rewards, totalRewardPoints);
    }

    /**
     * Internal method to calculate reward points
     * @param transactionEntity
     * @return Reward
     */

    private Reward calculateRewardPointsInternal(TransactionEntity transactionEntity) {
        return new Reward(transactionEntity.getCustomerId(), transactionEntity.getTotalAmount(),
                transactionEntity.getPurchaseDate().toLocalDate(), rewardPointsCalculationService
                .calculateRewardPoints(transactionEntity.getTotalAmount()));
    }
}
