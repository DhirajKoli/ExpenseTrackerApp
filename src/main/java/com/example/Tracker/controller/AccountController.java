package com.example.Tracker.controller;

import com.example.Tracker.dto.AccountDto;
import com.example.Tracker.model.Account;
import com.example.Tracker.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/apps/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") Long accountNumber){
        return new ResponseEntity<>(accountService.getAccount(accountNumber), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAlLAccountByUser(Principal principal){
        List<Account> accounts = accountService.getAlLAccountByUser(principal.getName());
        return new ResponseEntity<>(accounts,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto, Principal principal){
        Account account = accountService.createAccount(accountDto,principal.getName());
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Account>> getUsersAccounts(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(accountService.getUserAccounts(userId), HttpStatus.OK);
    }
}
