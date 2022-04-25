package com.vincent.demo.traning.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda_Predicate {

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
     * Predicate	        T	        boolean	斷言
     */
    public void base() {
        List<String> names = Arrays.asList("aaa", "bbb", "ccc");
        List<String> list = names.stream().filter(s -> s.startsWith("a")).collect(Collectors.toList());
        for (String s : list) {
            System.out.println("Predicate s = " + s);
        }
    }

    /**
     * Predicate<Integer> 可用 IntPredicate
     * Consumer<Integer> 可用 IntConsumer
     */
}
