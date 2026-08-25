package com.example.workshop.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the deliberately vulnerable Order Processor sample service.
 *
 * <p>This service exists only as a remediation target for the ATX Continuous
 * Modernization workshop. It intentionally uses outdated, CVE-affected
 * dependencies and at least one risky code path. Do not deploy it anywhere.
 */
@SpringBootApplication
public class OrderProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessorApplication.class, args);
    }
}
