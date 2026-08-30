package com.example.workshop.order;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class OrderController implements HttpHandler {

    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    /** Core logic, extracted so it can be unit-tested without a live server. */
    public String note(String value) {
        LOGGER.info("Received order note: " + value);
        return "logged";
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String value = queryParam(exchange.getRequestURI().getRawQuery(), "value");
        byte[] body = note(value).getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, body.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body);
        }
    }

    private static String queryParam(String rawQuery, String key) {
        if (rawQuery == null) {
            return "";
        }
        for (String pair : rawQuery.split("&")) {
            int eq = pair.indexOf('=');
            if (eq > 0 && pair.substring(0, eq).equals(key)) {
                return URLDecoder.decode(pair.substring(eq + 1), StandardCharsets.UTF_8);
            }
        }
        return "";
    }
}
