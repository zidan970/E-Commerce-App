package com.alta.e_commerce.controllers;

import com.alta.e_commerce.services.StoreService;
import com.alta.e_commerce.entities.User;
import com.alta.e_commerce.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping(
            path = "/stores",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<StoreResponse> create(@RequestBody StoreRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User genzaiNoShiyousha = (User) authentication.getPrincipal();
        System.out.println("your id: " + genzaiNoShiyousha.getUserId());
        
        request.setUserId(genzaiNoShiyousha.getUserId()); // Menggunakan setter untuk mengatur userId
        System.out.println("id request: " + request.getUserId());

        StoreResponse storeResponse = storeService.create(request);
        return WebResponse.<StoreResponse>builder()
                .message("success create store")
                .data(storeResponse)
                .build();
    }
}