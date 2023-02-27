package com.charter.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RewardsConfigTest {

    private RewardsConfig config;
    @BeforeEach
    void setUp() {
        config = new RewardsConfig();
    }

    @Test
    void testObjectMapper() {
        assertNotNull(config.objectMapper());
    }
}