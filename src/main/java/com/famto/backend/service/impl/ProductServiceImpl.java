package com.famto.backend.service.impl;

import com.famto.backend.dto.ProductDto;
import com.famto.backend.dto.ProductResponseDto;
import com.famto.backend.exception.MerchantNotFoundException;
import com.famto.backend.exception.ProductCreationException;
import com.famto.backend.model.Merchant;
import com.famto.backend.model.Product;
import com.famto.backend.repository.IMerchantRepository;
import com.famto.backend.repository.IProductRepository;
import com.famto.backend.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IMerchantRepository merchantRepository;
    private final IProductRepository productRepository;
    @Override
    public ProductResponseDto addProduct(ProductDto productDto) {
        Merchant merchant = merchantRepository.findById(productDto.getMerchantId())
                .orElseThrow(()-> new MerchantNotFoundException("Merchant Not found"));

        try {
            Product product = Product.builder()
                              .productName(productDto.getProductName())
                              .productPrice(productDto.getProductPrice())
                              .productDescription(productDto.getProductDescription())
                              .quantity(productDto.getQuantity())
                              .isDeleted(false)
                              .merchant(merchant)
                              .build();

          productRepository.save(product);
          return ProductResponseDto.builder().message("Product Added Successfully").build();
        }
        catch (DataIntegrityViolationException e){
            throw new ProductCreationException("Error entering product details");
        }
    }

    @Override
    public Page<Product> getAllProductsByMerchant(Long merchantId,Pageable pageable) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(()-> new MerchantNotFoundException("Merchant not found"));

        return productRepository.findByMerchant(merchant,pageable);
    }

    @Override
    public boolean deleteProduct(Long productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            productRepository.delete(product);
            return true;
        }
        else {
            return false;
        }
    }
}
