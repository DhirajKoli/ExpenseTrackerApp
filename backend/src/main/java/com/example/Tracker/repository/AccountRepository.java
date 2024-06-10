package com.example.Tracker.repository;


import com.example.Tracker.model.Account;
import com.example.Tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public List<Account> findAllAccountsByUser(User user);
}
