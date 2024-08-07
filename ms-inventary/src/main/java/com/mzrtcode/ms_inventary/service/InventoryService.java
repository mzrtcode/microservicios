package com.mzrtcode.ms_inventary.service;

import com.mzrtcode.ms_inventary.model.dto.BaseResponse;
import com.mzrtcode.ms_inventary.model.dto.OrderItem;
import com.mzrtcode.ms_inventary.model.entity.Inventory;
import com.mzrtcode.ms_inventary.repository.InventaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventaryRepository inventaryRepository;

    public boolean isInStock(String sku){
        Optional<Inventory> inventory = inventaryRepository.findBySku(sku);
        return inventory.filter(value -> value.getQuantity() > 0).isPresent();
    }

    public BaseResponse areInStock(List<OrderItem> orderItems){
        var errorList = new ArrayList<String>();

        List<String> skus = orderItems.stream()
                .map(OrderItem::getSku).toList();

        List<Inventory> inventoryList = inventaryRepository.findBySkuIn(skus);


        orderItems.forEach(orderItem -> {
            var inventary = inventoryList.stream().filter(value -> value.getSku().equals(orderItem.getSku())).findFirst();

            if(inventary.isEmpty()){
                errorList.add("Product with sku: " + orderItem.getSku() + " does not exist");
            }else if(inventary.get().getQuantity() < orderItem.getQuantity()){
                errorList.add("Product with sku: " + orderItem.getSku() + "has insufficient quantity");
            }
        });

        return errorList.size() > 0 ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null);
    }


}
