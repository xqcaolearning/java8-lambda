package com.tw.xqcao.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class MyStream<T> {

    private final List<T> list;

    public MyStream(List<T> list) {
        this.list = list;
    }

    public static <T> MyStream<T> generate(Supplier<List<T>> supplier) {
        return new MyStream<>(supplier.get());
    }

    public MyStream<T> filter(Predicate<T> predicate) {
        List<T> resultList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t))
                resultList.add(t);
        }
        return new MyStream<>(resultList);
    }

    public <R> MyStream<R> map(Function<T, R> function) {
        List<R> resultList = new ArrayList<>();
        for (T t : list) {
            resultList.add(function.apply(t));
        }
        return new MyStream<>(resultList);
    }

    public void forEach(Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public T reduce(BinaryOperator<T> binaryOperator) {
        boolean foundAny = false;
        T result = null;
        for (T t : list) {
            if (!foundAny) {
                foundAny = true;
                result = t;
            } else
                result = binaryOperator.apply(result, t);
        }
        return result;
    }

}
