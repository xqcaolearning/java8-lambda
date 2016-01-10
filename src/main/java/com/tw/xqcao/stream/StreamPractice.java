package com.tw.xqcao.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamPractice {

    public static void main(String args[]) {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));

        Stream stream = list.stream().filter(item -> item.startsWith("a"));

        //注入新元素
        list.add("ab");
        list.add("ac");

        stream.forEach(System.out::println);
    }
}
