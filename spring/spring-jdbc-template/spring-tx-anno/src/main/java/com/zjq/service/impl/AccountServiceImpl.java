package com.zjq.service.impl;

import com.zjq.dao.AccountDao;
import com.zjq.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>service</p>
 *
 * @Author zjq
 * @Date 2021/8/4
 */
@Service("accountService")
@Transactional(isolation = Isolation.REPEATABLE_READ,rollbackFor = RuntimeException.class)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void transfer(String outMan, String inMan, double money) {
        accountDao.out(outMan,money);
        int i = 1/0;
        accountDao.in(inMan,money);
    }

    //@Transactional(isolation = Isolation.DEFAULT)
    public void xxx(){}
}
