package com.htec;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by dbogicevic
 */
public class Proba {
    String ime;
    String prezime ;

    public static void main(String[] args) {
       List<String> a = new ArrayList<>();
       a.add("-1002");
        Optional<List<String>> a1 = Optional.of(a);
        if(a1.isPresent()){
            System.out.println("Postoji");
        }
        for(String s : a1.get()){
            System.out.println("ddlld");
        }
       if(a.size() == 1 && "1".equals(a.get(0))){
           System.out.println("dejan");
       }else{
           System.out.println("marko");
       }
       Optional<String>ad = Optional.of(a
                .stream()
                .filter( bs -> "-1002".equals(bs))
                .findFirst())
                .orElseGet(() -> Optional.empty());
       if(ad.isPresent())
        System.out.println(ad.get());
       else{
           System.out.println("kdkd");
       }
       for(String s : a1.get()){
           System.out.println("");
       }

    }





}

