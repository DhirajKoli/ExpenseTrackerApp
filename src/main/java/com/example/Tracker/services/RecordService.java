package com.example.Tracker.services;

import com.example.Tracker.dto.RecordDto;
import com.example.Tracker.model.Account;
import com.example.Tracker.model.Record;
import com.example.Tracker.model.SubCategory;
import com.example.Tracker.model.User;
import com.example.Tracker.repository.AccountRepository;
import com.example.Tracker.repository.RecordRepository;
import com.example.Tracker.repository.SubCategoryRepository;
import com.example.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    public Record postRecord(RecordDto recordDto) {

        Record record = new Record();
        Optional<Account> optionalFromAccount = null;
        Account fromAccount = null;
        Optional<Account> optionalToAccount = null;
        Account toAccount = null;

        record.setAmount(new BigDecimal(recordDto.getAmount()));
        record.setType(recordDto.getType());
        record.setDate(recordDto.getDate());
        record.setTime(recordDto.getTime());

        if(recordDto.getType().equals("Expense")){

            optionalFromAccount = accountRepository.findById(recordDto.getFromAccountId());
            if(!optionalFromAccount.isPresent())
                throw new RuntimeException("From Account not Found !!");
            fromAccount = optionalFromAccount.get();
            fromAccount.setBalance( fromAccount.getBalance().add(new BigDecimal(recordDto.getAmount())) );
            record.setUser(fromAccount.getUser());
            record.setFromAccount(accountRepository.save(fromAccount));

        } else if (recordDto.getType().equals("Income")) {

            optionalToAccount = accountRepository.findById(recordDto.getToAccountId());
            if(!optionalToAccount.isPresent())
                throw new RuntimeException("To Account not Found !!");
            toAccount = optionalToAccount.get();
            toAccount.setBalance( toAccount.getBalance().add(new BigDecimal(recordDto.getAmount())) );
            record.setUser(toAccount.getUser());
            record.setToAccount(accountRepository.save(toAccount));

        } else if (recordDto.getType().equals("Transfer")) {

            optionalFromAccount = accountRepository.findById(recordDto.getFromAccountId());
            if(!optionalFromAccount.isPresent())
                throw new RuntimeException("From Account not Found !!");
            fromAccount = optionalFromAccount.get();
            fromAccount.setBalance( fromAccount.getBalance().add(new BigDecimal(recordDto.getAmount())) );
            record.setUser(fromAccount.getUser());
            record.setFromAccount(accountRepository.save(fromAccount));

            optionalToAccount = accountRepository.findById(recordDto.getToAccountId());
            if(!optionalToAccount.isPresent())
                throw new RuntimeException("To Account not Found !!");
            toAccount = optionalToAccount.get();
            toAccount.setBalance( toAccount.getBalance().add(new BigDecimal(recordDto.getAmount())) );
            record.setUser(toAccount.getUser());
            record.setToAccount(accountRepository.save(toAccount));

        } else {
            throw new RuntimeException("Record Type Invalid !!");
        }

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(recordDto.getSubCategoryId());
        if(!optionalSubCategory.isPresent())
            throw new RuntimeException("SubCategory not Found !!");
        record.setSubCategory(optionalSubCategory.get());

        return recordRepository.save(record);
    }

    public Record getRecord(Long recordId) {
        Optional<Record> optionalRecord = recordRepository.findById(recordId);
        if(!optionalRecord.isPresent())
            throw new RuntimeException("Record not Found !!");
        return optionalRecord.get();
    }

    public List<Record> getAllRecordsByLoggedUser(String username) {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if(!optionalUser.isPresent())
            throw new RuntimeException("User not Found !!");
        return recordRepository.findByUser(optionalUser.get());
    }
}
