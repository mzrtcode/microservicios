package com.mzrtcode.ms_orders.service;

import com.mzrtcode.ms_orders.mapper.OrderMapper;
import com.mzrtcode.ms_orders.model.dto.OrderRequest;
import com.mzrtcode.ms_orders.model.dto.OrderResponse;
import com.mzrtcode.ms_orders.model.entity.Order;
import com.mzrtcode.ms_orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ResourceUrlProvider mvcResourceUrlProvider;


    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toOrder(orderRequest);
        order.setOrderNumber(UUID.randomUUID().toString());

        //TODO: Check products in inventary
        Order savedOrder = orderRepository.save(order);
        log.info("Order saved: {}", order);
        return orderMapper.toOrderResponse(savedOrder);
    }
}
