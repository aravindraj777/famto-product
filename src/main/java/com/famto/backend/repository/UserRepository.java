package com.famto.backend.repository;

import com.famto.backend.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Merchant,Long> {

    Optional<Merchant> findByEmail(String email);
}
