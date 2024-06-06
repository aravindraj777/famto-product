package com.famto.backend.controller;

import com.famto.backend.model.Admin;
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
    public ResponseEntity<Object> getDemo(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Admin currentUser = (Admin) authentication.getPrincipal();
//        return ResponseEntity.ok(currentUser);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof Admin){
            Admin currentUser = (Admin) principal;
            return ResponseEntity.ok(currentUser);
        } else if (principal instanceof Merchant) {
            Merchant currentUser = (Merchant) principal;
            return ResponseEntity.ok(currentUser);
        }
        else {
            throw new IllegalStateException("User type unexpected"+principal.getClass());
        }
    }
}
