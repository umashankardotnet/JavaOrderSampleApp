package com.example.workshop.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Minimal test so the Security Agent's remediation can be validated by a
 * build + test run: after the agent bumps log4j to a fixed version, this must
 * still pass — that's the "verify" gate in the continuous loop.
 */
class OrderControllerTest {

    private final OrderController controller = new OrderController();

    @Test
    void noteIsLogged() {
        assertEquals("logged", controller.note("hello"));
    }
}
