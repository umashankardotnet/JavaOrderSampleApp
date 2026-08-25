package com.example.workshop.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Minimal test so the Security Agent's remediation can be validated by a
 * build+test run. After the agent bumps dependency versions, these tests must
 * still pass — that's the "verify" gate in the continuous loop.
 */
class OrderControllerTest {

    private final OrderController controller = new OrderController();

    @Test
    void healthReturnsOk() {
        assertEquals("OK", controller.health());
    }

    @Test
    void noteIsLogged() {
        assertEquals("logged", controller.note("hello"));
    }

    @Test
    void labelSubstitutesKnownVariable() {
        assertEquals("order 12345", controller.label("order ${orderId}"));
    }
}
