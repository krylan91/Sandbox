package me.aakrylov.sandbox.tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyCalculations {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyCalculations.class);

    @Test
    void currencyCalculations() {
        double z = ((599.99d - 0.98d) - 599.00d) - 0.01d;
        if (z == 0d) {
            logger.info("z == 0");
        } else if (z > 0d){
            logger.info("z > 0");
            logger.warn("z = {}", z);
        } else {
            logger.info("z < 0");
            logger.warn("z = {}", z);
        }
    }
}
