package com.zjq.mapper;

import com.zjq.domain.Account;

import java.util.List;

public interface AccountMapper {

     void save(Account account);

     List<Account> findAll();

}
