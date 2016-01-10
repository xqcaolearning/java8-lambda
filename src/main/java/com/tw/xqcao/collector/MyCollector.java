package com.tw.xqcao.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyCollector {

    public static Collector<String, StringBuilder, String> myJoinCollector(String separate) {
        return Collector.of(
                StringBuilder::new,
                (sb, s) -> sb.append(s).append(separate),
                (sb1, sb2) -> sb1.append(sb2).append(separate),
                (sb) -> sb.substring(0, sb.length() - separate.length()),
                Collector.Characteristics.CONCURRENT
        );
    }

    public static void main(String args[]) {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "ab", "ac"));

        //使用Collectors类去实现。
        String result = list.stream().collect(Collectors.joining("++"));
        System.out.println("Result: " + result);

        //使用自定义Collector去实现
        String result2 = list.stream().collect(myJoinCollector("--"));
        System.out.println("Result2: " + result2);
    }
}
