package com.charter;

import com.charter.controller.RewardsController;
import com.charter.controller.TransactionController;
import com.charter.converter.RewardRequestConverter;
import com.charter.converter.RewardResponseConverter;
import com.charter.repository.TransactionRepository;
import com.charter.repository.TransactionRepositoryImpl;
import com.charter.service.RewardPointsCalculationService;
import com.charter.service.RewardsService;
import com.charter.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class RewardsConfig {

    @Bean
    public DataSource getDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public TransactionRepository getRewardsRepository() {
        return new TransactionRepositoryImpl(getJdbcTemplate());
    }

    @Bean
    public RewardPointsCalculationService getRewardPointsCalculationService() {
        return new RewardPointsCalculationService();
    }

    @Bean
    public RewardResponseConverter getRewardResponseConverter() {
        return new RewardResponseConverter(getRewardPointsCalculationService());
    }

    @Bean
    public RewardRequestConverter getRewardRequestConverter() {
        return new RewardRequestConverter();
    }

    @Bean
    public RewardsService getRewardsService() {
        return new RewardsService(getRewardResponseConverter(), getRewardsRepository());
    }

    @Bean
    public TransactionService getTransactionService() {
        return new TransactionService(getRewardsRepository());
    }

    @Bean
    public RewardsController getRewardsController() {
        return new RewardsController(getRewardsService());
    }

    @Bean
    public TransactionController getTransactionController() {
        return new TransactionController(getTransactionService(), getRewardRequestConverter());
    }


}
