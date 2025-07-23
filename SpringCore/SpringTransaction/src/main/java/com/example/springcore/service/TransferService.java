package com.example.springcore.service;

import com.example.springcore.entity.Account;
import com.example.springcore.entity.TransactionHistory;
import com.example.springcore.repository.AccountRepository;
import com.example.springcore.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private HistoryRepository historyRepo;

    @Transactional
    public void transfer(int fromId, int toId, double amount) {
        Account from = accountRepo.findById(fromId);
        Account to = accountRepo.findById(toId);

        if (from.getBalance() < amount) {
            throw new RuntimeException("Số dư không đủ!");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountRepo.update(from);
        accountRepo.update(to);


        TransactionHistory history = new TransactionHistory();
        history.setFromAccountId(fromId);
        history.setToAccountId(toId);
        history.setAmount(amount);
        historyRepo.save(history);

    }
}
