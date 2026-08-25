package com.example.workshop.order;

import org.apache.commons.text.StringSubstitutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST endpoints for the Order Processor.
 */
@RestController
public class OrderController {

    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    /**
     * Echoes a note back to the caller.
     *
     * <p>User input is logged. On log4j-core 2.17+, message lookup is disabled
     * by default, mitigating CVE-2021-44228.
     */
    @GetMapping("/orders/note")
    public String note(@RequestParam("value") String value) {
        LOGGER.info("Received order note: " + value);
        return "logged";
    }

    /**
     * "Renders" a templated label for an order.
     *
     * <p>Uses a map-only StringSubstitutor (no script/DNS/URL interpolators).
     * On commons-text 1.10+, the dangerous default interpolators are disabled,
     * mitigating CVE-2022-42889.
     */
    @GetMapping("/orders/label")
    public String label(@RequestParam("template") String template) {
        StringSubstitutor substitutor = new StringSubstitutor(Map.of("orderId", "12345"));
        return substitutor.replace(template);
    }
}
