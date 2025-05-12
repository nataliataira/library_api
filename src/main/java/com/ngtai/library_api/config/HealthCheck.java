package com.ngtai.library_api.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class HealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            if (ip.isReachable(10000)) {
                return Health.up().build();
            }
        } catch (Exception ex) {
            return Health.down().withDetail("error", ex.getMessage()).build();
        }
        return Health.down().withDetail("error", "unknown").build();
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }
}
