package com.ivyzh.filter.proxy;

public class Lenovo implements SaleComputer {

    @Override
    public String sale(int money) {
        System.out.println("花了"+money+"元买了联想电脑");
        return "联想电脑";
    }

    @Override
    public void show() {
        System.out.println("展示电脑");
    }
}
