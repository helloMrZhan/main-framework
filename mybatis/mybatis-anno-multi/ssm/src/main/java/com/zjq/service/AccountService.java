package com.zjq.service;

import com.zjq.domain.Account;

import java.util.List;

public interface AccountService {

    public void save(Account account);

    public List<Account> findAll();

}
