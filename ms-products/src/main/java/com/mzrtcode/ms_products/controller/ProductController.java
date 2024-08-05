package com.mzrtcode.ms_products.controller;

import com.mzrtcode.ms_products.model.dto.ProductRequest;
import com.mzrtcode.ms_products.model.dto.ProductResponse;
import com.mzrtcode.ms_products.model.entity.Product;
import com.mzrtcode.ms_products.repository.ProductRepository;
import com.mzrtcode.ms_products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduc(@RequestBody ProductRequest product) {

        ProductResponse productResponse = productService.addProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }


}
