package com.mzrtcode.ms_orders.controller;

import com.mzrtcode.ms_orders.model.dto.OrderRequest;
import com.mzrtcode.ms_orders.model.dto.OrderResponse;
import com.mzrtcode.ms_orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderResponse.getId())
                .toUri();
        return ResponseEntity.created(location).body(orderResponse);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
