package com.mzrtcode.ms_orders.service;

import com.mzrtcode.ms_orders.mapper.OrderMapper;
import com.mzrtcode.ms_orders.model.dto.BaseResponse;
import com.mzrtcode.ms_orders.model.dto.OrderRequest;
import com.mzrtcode.ms_orders.model.dto.OrderResponse;
import com.mzrtcode.ms_orders.model.entity.Order;
import com.mzrtcode.ms_orders.model.entity.OrderItem;
import com.mzrtcode.ms_orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;


    public OrderResponse placeOrder(OrderRequest orderRequest) {

        BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/api/inventary/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();

        if(result != null && !result.hasErrors()){
            Order order = orderMapper.toOrder(orderRequest);
            order.setOrderNumber(UUID.randomUUID().toString());


            for(OrderItem item: order.getOrderItems()){
                item.setOrder(order);
            }


            Order savedOrder = orderRepository.save(order);
            log.info("Order saved: {}", order);
            return orderMapper.toOrderResponse(savedOrder);
        }else{
            throw new IllegalArgumentException("Some of the products are not in stock");
        }
    }

    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toOrderResponse(orders);
    }
}
