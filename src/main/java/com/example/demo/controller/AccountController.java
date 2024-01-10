package com.example.demo.controller;

import com.example.demo.model.AccountInfo;
import com.example.demo.model.RequestQueue;
import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/post")
    public ResponseEntity<AccountInfo> saveAccountInfo(@RequestBody RequestQueue requestQueue) throws Exception {
        return ResponseEntity.ok(accountService.transact(requestQueue));
    }
}
