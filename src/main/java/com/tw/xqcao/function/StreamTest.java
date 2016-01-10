package com.tw.xqcao.function;

import java.util.Arrays;
import java.util.List;

public class StreamTest {

    public static List<People> getPeopleList() {
        return Arrays.asList(
                new People("王一一", 11), new People("王二二", 22),
                new People("张一一", 33), new People("张二二", 44)
        );
    }

    public static void main(String args[]) {
        MyStream<People> stream = MyStream.generate(StreamTest::getPeopleList);

        //求出所有人的年龄总和
        Integer sum = stream.map(People::getAge).reduce((a1, a2) -> a1 + a2);
        System.out.println("所有人的年龄总和为：" + sum);

        //输出所有姓张的人名
        stream.filter(people -> people.getName().startsWith("张"))
                .forEach(people -> System.out.println(people.getName()));

        //求出所有姓王的人的年龄总和
        Integer sum2 = stream.filter(people -> people.getName().startsWith("王"))
                            .map(People::getAge).reduce((a1, a2) -> a1 + a2);
        System.out.println("所有姓王的人的年龄总和为：" + sum2);
    }

    static class People {
        private final Integer age;
        private final String name;

        public People(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }
}
