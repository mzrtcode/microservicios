package com.mzrtcode.ms_products.service;

import com.mzrtcode.ms_products.mapper.ProductMapper;
import com.mzrtcode.ms_products.model.dto.ProductRequest;
import com.mzrtcode.ms_products.model.dto.ProductResponse;
import com.mzrtcode.ms_products.model.entity.Product;
import com.mzrtcode.ms_products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(product);
        log.info("Product saved : {}", product);
        return productMapper.toProductResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productMapper.toProductResponseList(productList);
    }
}
