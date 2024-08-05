package com.mzrtcode.ms_products.mapper;

import com.mzrtcode.ms_products.model.dto.ProductRequest;
import com.mzrtcode.ms_products.model.dto.ProductResponse;
import com.mzrtcode.ms_products.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toProduct(ProductRequest productRequest);
    ProductResponse toProductResponse(Product product);
    List<ProductResponse> toProductResponseList(List<Product> productList);
}
