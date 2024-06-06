package com.famto.backend.controller;

import com.famto.backend.model.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchant")
public class MerchantController {


    @GetMapping("/demo")
    public ResponseEntity<Merchant> getDemo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Merchant currentUser = (Merchant) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }
}
