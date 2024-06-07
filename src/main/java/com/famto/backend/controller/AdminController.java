package com.famto.backend.controller;

import com.famto.backend.dto.RegisterMerchantDto;
import com.famto.backend.model.Merchant;
import com.famto.backend.service.IAuthenticationService;
import com.famto.backend.service.IMerchantService;
import com.famto.backend.service.JwtService;
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
@RequestMapping("/api/v1/admin")
public class AdminController {


    private final JwtService jwtService;
    private final IMerchantService merchantService;
    private final IAuthenticationService authenticationService;

    @PostMapping("/register-merchant")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Merchant> registerMerchant(
            @RequestBody RegisterMerchantDto registerMerchantDto){

        System.out.println(2);
        Merchant registeredUser = authenticationService.registerMerchant(registerMerchantDto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/getAll-merchants")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Merchant>> getAllMerchants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        Pageable pageable = PageRequest.of(page,size);
        Page<Merchant> merchantPage = merchantService.getAllMerchants(pageable);
        List<Merchant> merchants = merchantPage.getContent().stream().toList();
        return ResponseEntity.ok(merchants);
    }

    @DeleteMapping("/delete-merchant/{merchantId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteMerchant(
            @PathVariable(name = "merchantId") Long id){

        return ResponseEntity.ok(merchantService.deleteProduct(id));
    }

}
