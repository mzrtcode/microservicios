package com.mzrtcode.ms_inventary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    private Long id;
    private String sku;
    private Double price;
    private Long quantity;
}
