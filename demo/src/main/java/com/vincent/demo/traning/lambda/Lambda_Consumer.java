package com.vincent.demo.traning.lambda;

import java.util.Arrays;
import java.util.List;

public class Lambda_Consumer {

    /*
     * UnaryOperator	    T	T	    一元函數，輸入輸出類型相同
     * Predicate	        T	        boolean	斷言
     * Consumer	            T	/	    消費一個數據，只有輸入沒有輸出
     * Function<T,R>	    T	R	    輸入 T 返回 R，有輸入也有輸出
     * Supplier	            /	T	    提供一個數據，沒有輸入只有輸出
     * BiFunction<T,U,R>	(T,U)	R	兩個輸入參數
     * BiPredicate<L, R>	(L,R)	boolean	兩個輸入參數
     * BiConsumer<T, U>	    (T,U)	void	兩個輸入參數
     * BinaryOperator	    (T,T)	T	二元函數，輸入輸出類型相同
     * */

    /**
     * Consumer	            T	/	    消費一個數據，只有輸入沒有輸出
     */
    public void base() {
        List<String> names = Arrays.asList("aaa", "bbb", "ccc");
        names.stream().forEach(s -> System.out.println(s));
    }
}
