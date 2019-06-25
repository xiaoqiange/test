package com.util;

public class Pass {
    public void change(P p){
//        p=new P();
        p.age=20;
    }
    public static void main(String[] args) {
        Pass pass=new Pass();
        P p=new Pass().new P();
        System.out.println("年龄："+p.age);
        p.age=19;
        pass.change(p);
        System.out.println("年龄："+p.age);
    }
    class P{
        int age=18;
    }
}
