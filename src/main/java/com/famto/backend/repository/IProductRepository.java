package com.famto.backend.repository;

import com.famto.backend.model.Merchant;
import com.famto.backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {


    Page<Product> findByMerchant(Merchant merchant, Pageable pageable);

}
