package com.famto.backend.service;

import com.famto.backend.dto.ProductDto;
import com.famto.backend.dto.ProductResponseDto;
import com.famto.backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    ProductResponseDto addProduct(ProductDto productDto);

    Page<Product> getAllProductsByMerchant(Long merchantId,Pageable pageable);

    boolean deleteProduct(Long productId);
}
