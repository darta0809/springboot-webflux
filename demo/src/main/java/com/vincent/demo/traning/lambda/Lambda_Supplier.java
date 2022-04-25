package com.vincent.demo.traning.lambda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Supplier;

public class Lambda_Supplier {

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
     * Supplier	            /	T	    提供一個數據，沒有輸入只有輸出
     */
    public void base() {
        Supplier<Connection> supplier = () -> {
            Connection con = null;
            try {
                con = DriverManager.getConnection("", "", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return con;
        };
        Connection connection = supplier.get();
    }

}
