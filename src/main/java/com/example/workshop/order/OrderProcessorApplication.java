package com.example.workshop.order;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class OrderProcessorApplication {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/orders/note", new OrderController());
        server.setExecutor(null);
        server.start();
        System.out.println("Order Processor listening on http://localhost:8080/orders/note?value=...");
    }
}
