package com.mzrtcode.ms_inventary.controller;

import com.mzrtcode.ms_inventary.model.dto.BaseResponse;
import com.mzrtcode.ms_inventary.model.dto.OrderItem;
import com.mzrtcode.ms_inventary.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventaryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku}")
    public ResponseEntity<Boolean> isInStock(@PathVariable("sku") String sku){
        boolean inStock = inventoryService.isInStock(sku);
        return ResponseEntity.ok(inStock);
    }

    @PostMapping("/in-stock")
    public ResponseEntity<BaseResponse> areInStock(@RequestBody List<OrderItem> orderItems){
        BaseResponse baseResponse = inventoryService.areInStock(orderItems);
        return ResponseEntity.ok(baseResponse);
    }



}
