package com.tw.xqcao.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


interface Function<T, R> {
    R fun(T t);
}

interface Effect1<T> {
    void fun(T t);
}

public class MyList<T> {

    private List<T> items;

    public MyList() {
        items = new ArrayList<T>();
    }

    public MyList(T ... args) {
        items = Arrays.asList(args);
    }

    public void add(T t) {
        items.add(t);
    }

    //Filter简单实现
    public MyList<T> filter(Function<T, Boolean> filterFun) {
        MyList<T> result = new MyList<T>();
        for (T t : items) {
            if (filterFun.fun(t)) {
                result.add(t);
            }
        }
        return result;
    }

    //遍历简单实现
    public void forEach(Effect1<T> eachFun) {
        for (T t : items) {
            eachFun.fun(t);
        }
    }

    public static void main(String args[]) {
        MyList<String> oldUsage = new MyList<String>("aa", "ba", "ac", "da");

        //Java8之前的调用方式
        oldUsage.filter(new Function<String, Boolean>() {
            @Override
            public Boolean fun(String s) {
                return s.startsWith("a");
            }
        }).forEach(new Effect1<String>() {
            @Override
            public void fun(String s) {
                System.out.print(" " + s);
            }
        });

        System.out.println("\n------------------------\n");

        //现在的调用方式
        oldUsage.filter(item -> item.startsWith("a"))
                .forEach(item -> System.out.print(" " + item));
    }
}