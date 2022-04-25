package com.vincent.demo.traning.lambda;

import java.util.function.UnaryOperator;
import lombok.Data;

public class Lambda_UnaryOperAtor {

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
     * UnaryOperator	    T	T	    一元函數，輸入輸出類型相同
     */
    public void base() {
        User2 user = new User2();
        user.setUsername("javaBoy");
        UnaryOperator<String> func = (username) -> "hello " + username;
        String say = user.say(func);
        System.out.println("UnaryOperator say " + say);
    }

}

@Data
class User2 {

    private String username;

    public String say(UnaryOperator<String> sayHello) {
        return sayHello.apply(this.username);
    }
}