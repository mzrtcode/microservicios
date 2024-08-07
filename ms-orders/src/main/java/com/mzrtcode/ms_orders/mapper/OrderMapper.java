package com.mzrtcode.ms_orders.mapper;

import com.mzrtcode.ms_orders.model.dto.OrderRequest;
import com.mzrtcode.ms_orders.model.dto.OrderResponse;
import com.mzrtcode.ms_orders.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    Order toOrder(OrderRequest orderRequest);
    OrderResponse toOrderResponse(Order order);
    List<OrderResponse> toOrderResponse(List<Order> orders);
}
