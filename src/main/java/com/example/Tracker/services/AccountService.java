package com.example.Tracker.services;

import com.example.Tracker.dto.AccountDto;
import com.example.Tracker.model.Account;
import com.example.Tracker.model.User;
import com.example.Tracker.repository.AccountRepository;
import com.example.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public Account getAccount(Long accountNumber){
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);

        Account account = null;
        if(optionalAccount.isPresent()){
            account = optionalAccount.get();
        }else{
            throw new RuntimeException("Account Not Found!!");
        }
        account.setUser(null);
        return account;
    }

    public Account createAccount(AccountDto accountDto,String username){
        Account account = new Account();
        account.setName(accountDto.getName());
        account.setBalance(accountDto.getBalance());
        account.setUser(userService.getUser(username));
        Account newAccount = accountRepository.save(account);
        newAccount.setUser(null);
        return newAccount;
    }

    public List<Account> getAlLAccountByUser(String username) {
        User user = userService.getUser(username);

        List<Account> accountList = accountRepository.findAllAccountsByUser(user);

        accountList.forEach(account -> account.setUser(null));
        return accountList;
    }
}
