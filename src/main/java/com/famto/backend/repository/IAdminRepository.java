package com.famto.backend.repository;


import com.famto.backend.enums.Role;
import com.famto.backend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin> findByEmail(String email);

    Admin findByRole(Role role);
}
