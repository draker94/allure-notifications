package guru.qa.allure.notifications.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kadehar
 * @since 4.0
 * Utility class for percentage calculation.
 */
@Slf4j
public class Percentage {
    public double eval(int result, int total) {
        log.info("Calculate percentage for result {} by total {}", result,
                total);
        return (result * 100.00) / total;
    }
}
