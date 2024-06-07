package com.famto.backend.controller;


import com.famto.backend.dto.ProductDto;
import com.famto.backend.dto.ProductResponseDto;
import com.famto.backend.model.Product;
import com.famto.backend.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('MERCHANT')")
@RequestMapping("/api/v1/merchant/products")
public class MerchantProductController {

    private final IProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody ProductDto productDto){
        ProductResponseDto addedProduct = productService.addProduct(productDto);
        return ResponseEntity.ok(addedProduct);
    }

    @GetMapping("/all-products/{merchantId}")
    public ResponseEntity<List<Product>> getAllProducts(
                    @PathVariable(name = "merchantId") Long merchantId,
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "10") int size

    ){

        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productService.getAllProductsByMerchant(merchantId,pageable);
        List<Product> products = productPage.getContent().stream().filter(product -> !product.getIsDeleted()).toList();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<Boolean> deleteProduct(
                            @PathVariable(name = "productId") Long id
    ){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
