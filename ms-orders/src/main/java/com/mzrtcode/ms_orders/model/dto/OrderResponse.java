package com.mzrtcode.ms_orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;
    private String orderNumber;
    private List<OrderItem> orderItems;
}
