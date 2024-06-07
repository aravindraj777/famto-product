package com.famto.backend.service;

import com.famto.backend.model.Merchant;
import com.famto.backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IMerchantService {



    Page<Merchant> getAllMerchants(Pageable pageable);

    boolean deleteProduct(Long merchantId);

}
