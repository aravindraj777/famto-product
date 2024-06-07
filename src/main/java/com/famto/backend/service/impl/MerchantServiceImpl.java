package com.famto.backend.service.impl;

import com.famto.backend.model.Merchant;
import com.famto.backend.model.Product;
import com.famto.backend.repository.IMerchantRepository;
import com.famto.backend.service.IMerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements IMerchantService {

    private final IMerchantRepository merchantRepository;




    @Override
    public Page<Merchant> getAllMerchants(Pageable pageable) {
        return merchantRepository.findAll(pageable);
    }

    @Override
    public boolean deleteProduct(Long merchantId) {

        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()){
           Merchant merchant = merchantOptional.get();
           merchantRepository.delete(merchant);
            return true;
        }
        else {
            return false;
        }
    }
}
