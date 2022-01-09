package com.zjq.aop;

public class Target implements TargetInterface {
    @Override
    public void save() {
        System.out.println("save running.....");
        //int i = 1/0;
    }
}
