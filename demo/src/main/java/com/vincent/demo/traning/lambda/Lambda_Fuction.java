package com.vincent.demo.traning.lambda;

import java.util.function.Function;
import lombok.Data;

public class Lambda_Fuction {

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
     * 調用 <Function<String,String>，第一個泛型代表輸入，第二個代表輸出
     */
    public void base() {
        User user = new User();
        user.setUsername("javaboy");
        String say = user.say((username) -> "hello " + username);
        System.out.println("Function say = " + say);
    }

    /**
     * Function 支持鏈式
     */
    public void fuctionBase() {
        User user = new User();
        user.setUsername("javaboy");
        Function<String, String> fuc = (username) -> "hello " + username;
        String say = user.say(fuc.andThen(s -> "你好 " + s));
        System.out.println("Function say " + say);
    }
}

@Data
class User {

    private String username;

    public String say(Function<String, String> sayHello) {
        return sayHello.apply(this.username);
    }
}
