package com.mzrtcode.ms_products.service;

import com.mzrtcode.ms_products.mapper.ProductMapper;
import com.mzrtcode.ms_products.model.dto.ProductRequest;
import com.mzrtcode.ms_products.model.dto.ProductResponse;
import com.mzrtcode.ms_products.model.entity.Product;
import com.mzrtcode.ms_products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductService productService;

    private ProductRequest productRequest;
    private Product product;
    private ProductResponse productResponse;

    @BeforeEach
    void setUp() {
        productRequest = ProductRequest.builder()
                .sku("123456")
                .name("Keyboard HyperX")
                .description("Mechanical")
                .price(150.22)
                .build();

        product = Product.builder()
                .sku("123456")
                .name("Keyboard HyperX")
                .description("Mechanical")
                .price(150.22)
                .build();

        productResponse = ProductResponse.builder()
                .id(1L)
                .sku("123456")
                .name("Keyboard HyperX")
                .description("Mechanical")
                .price(150.22)
                .build();
    }

    @Test
    void createProduct_success() {
        when(productMapper.toProduct(productRequest)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toProductResponse(product)).thenReturn(productResponse);

        // ACTION
        ProductResponse result = productService.addProduct(productRequest);

        // ASSERT
        assertEquals(productResponse, result);

        //VERIFY
        verify(productMapper).toProduct(productRequest);
        verify(productRepository).save(product);
        verify(productMapper).toProductResponse(product);
    }


    @Test
    void getAllProducts() {
    }
}