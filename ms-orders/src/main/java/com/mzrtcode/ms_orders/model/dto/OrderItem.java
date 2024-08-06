package com.mzrtcode.ms_orders.model.dto;

import com.mzrtcode.ms_orders.model.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    private String sku;
    private Double price;
    private Long quantity;
    private Order order;
}
