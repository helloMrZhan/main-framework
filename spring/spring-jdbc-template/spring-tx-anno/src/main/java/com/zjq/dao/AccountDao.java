package com.zjq.dao;

public interface AccountDao {

    public void out(String outMan, double money);
    public void in(String inMan, double money);

}
