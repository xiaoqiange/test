package cn.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestSail {
    public void sail(Sail sail){
        sail.init();
    }
    public void sail1(LambdaSail sail){
        sail.getValue("匿名内部类！");
    }
    
    public static void main(String[] args) {
        TestSail sail = new TestSail();
//        sail.sail("is str!").init();
        sail.sail(() -> System.out.println("Hello World!"));
        sail.sail1(str -> System.out.println("Hello World!"+str));
        
        String[] atp = {"Rafael Nadal", "Novak Djokovic",  
                "Stanislas Wawrinka",  
                "David Ferrer","Roger Federer",  
                "Andy Murray","Tomas Berdych",  
                "Juan Martin Del Potro"};  
         List<String> players =  Arrays.asList(atp);  
         players.forEach((player)->System.out.println("this name is "+player));
         Collections.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));  
    }

}
