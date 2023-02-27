package com.charter.record;

import java.util.List;

public record RewardResponse(List<Reward> rewards, int totalRewardPoints) {
}
