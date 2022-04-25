package com.vincent.demo.traning.lambda;

import java.util.Arrays;
import java.util.Random;
import java.util.function.*;

public class Lambda_Base {

    @FunctionalInterface
    interface ICalculator {

        int square(int i);
    }

    /**
     * 單個參數可直接寫參數，多個參數需要加 ( )
     */
    public void singleParam() {
        ICalculator ic = i -> i * i;
        int square = ic.square(5);
        System.out.println("square = " + square);
    }

    /**
     * 要寫參數類型的，正常來講是不用的
     */
    public void singleParamWriteType() {
        ICalculator ic = (int i) -> i * i;
        int square = ic.square(6);
        System.out.println("square = " + square);
    }

    /**
     * 方法不止一行則需要用 { }，只有一行的話就不用
     */
    public void methodMoreThanOneLine() {
        ICalculator ic = i -> {
            i = i * i;
            i++;
            return i;
        };
        int result = ic.square(2);
        System.out.println("result = " + result);
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * function 靜態方法引用
     */
    public void functionStaticMethod() {
        Function<Integer, String> func = a -> String.valueOf(a);
        String s = func.apply(999);
        System.out.println("function staticMethod s = " + s);

        // 可寫成
        Function<Integer, String> func2 = String::valueOf;
        String s2 = func.apply(999);
        System.out.println("function staticMethod s = " + s2);
    }

    /**
     * consumer 靜態方法引用
     */
    public void consumerStaticMethod() {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("consumer javaboy");

        // 可寫成
        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("consumer javaboy");
    }

    /**
     * BiFunction
     */
    public void biFunctionStaticMethod() {
        BiFunction<Integer, Integer, Double> func = (a, b) -> Math.pow(a, b);
        Double result = func.apply(3, 4);
        System.out.println("biFunction result = " + result);

        // 可寫成
        BiFunction<Integer, Integer, Double> func2 = Math::pow;
        Double result2 = func2.apply(3, 4);
        System.out.println("biFunction result = " + result2);
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * unaryOperator 實例化
     */
    public void newObjectMethod() {
        Random random = new Random();
        IntUnaryOperator func = i -> random.nextInt(i);
        int r = func.applyAsInt(10);
        System.out.println("r = " + r);

        // 可寫成
        Random random2 = new Random();
        IntUnaryOperator func2 = random2::nextInt;
        int r2 = func2.applyAsInt(10);
        System.out.println("r = " + r2);

        // 字串的實例比較特殊一些
        String[] stringArray = {"Barbara", "Mary", "James"};
        Arrays.sort(stringArray, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(stringArray));

        // 使用方法引用
        String[] stringArray2 = {"Barbara", "Mary", "James"};
        Arrays.sort(stringArray2, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(stringArray2));
    }

    /**
     * 構造方法引用
     */
    public void constructionMethod() {
        Supplier<Cat> supplier = () -> new Cat();
        Cat cat = supplier.get();

        // 可寫成
        Supplier<Cat> supplier2 = Cat::new;
        Cat cat2 = supplier2.get();
    }

    /**
     * 陣列構造方法
     */
    public void arrayConstructionMethod() {
        IntFunction<int[]> func = (i) -> new int[i];
        int[] arr = func.apply(10);
        System.out.println("arr.length = " + arr.length);

        IntFunction<int[]> func2 = int[]::new;
        int[] arr2 = func2.apply(10);
        System.out.println("arr.length = " + arr2.length);
    }

    /**
     * 外部變數引用
     */
    public void externalParam() {
        // 內部使用外部變數，需要這個變數是 final 類型
        String s = "javaboy";
        // s = "test"; 若是改變，將會抱錯
        Consumer<String> consumer = s1 -> System.out.println(s1 + s);
        consumer.accept("hello ");
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * 類型推斷，遇到 overload 時，就需要強轉
     */
    public void typeInference() {
        calculator((ICalculator2) (a, b) -> a + b);
        // 可寫成
        calculator((ICalculator3) Integer::sum);
    }

    public static void calculator(ICalculator2 iCalculator) {
    }

    public static void calculator(ICalculator3 iCalculator) {
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * 多層表達式
     */
    public void multilevelExpressions() {
        /*
         * 從右往左看
         * z-> x + y + z 對應的是 IntFunction<Integer>
         * y -> z-> x + y + z 整體做為返回，y 做為輸入，對應的是 Function<Integer, IntFunction<Integer>>
         * x -> y -> z -> x + y + z 整體做為返回，x 做為輸入，對應的就是 Function<Integer, Function<Integer, IntFunction<Integer>>>
         * */
        Function<Integer, Function<Integer, IntFunction<Integer>>> func = x -> y -> z -> x + y + z;
        Integer i = func.apply(3).apply(4).apply(5);
        System.out.println("i = " + i);
    }

}

@FunctionalInterface
interface ICalculator2 {

    int add(int a, int b);
}

@FunctionalInterface
interface ICalculator3 {

    int multiply(int a, int b);
}

class Cat {

}
