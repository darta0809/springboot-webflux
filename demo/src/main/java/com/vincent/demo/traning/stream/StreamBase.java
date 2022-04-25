package com.vincent.demo.traning.stream;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.*;
import lombok.AllArgsConstructor;
import lombok.Data;

public class StreamBase {

    public void base() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        // 求平均
        double asDouble = IntStream.of(arr).average().getAsDouble();
        // 求總和
        int sum = IntStream.of(arr).sum();

        System.out.println(asDouble);
        System.out.println(sum);

        // map 加工，可以把一個 Stream 對象轉為另一個 Stream 對象
        double[] arr2 = {1, 2, 3, 4, 5, 6};
        double sum2 = DoubleStream.of(arr2).map(i -> Math.pow(i, 2)).sum();

        System.out.println(sum2);
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * 創建 Stream
     */
    public void createCollectionStream() {
        List<String> list = new ArrayList<>();
        Stream<String> s1 = list.stream();
        Set<String> set = new HashSet<>();
        Stream<String> s2 = set.stream();
    }

    public void createArrayStream() {
        IntStream stream = Arrays.stream(new int[]{11, 22, 33, 44, 55, 66});
    }

    public void createNumberStream() {
        IntStream s1 = IntStream.of(1, 2, 3);
        DoubleStream s2 = DoubleStream.of(1, 2, 3);
        LongStream s3 = LongStream.of(1L, 2L, 3L);
    }

    /**
     * Stream.generate 方法可以自己創建一個 stream，自己創建的時候需要提供一個 Supplier，通過調用 Supplier 中的 get 方法自動獲取元素
     */
    public void createCustom() {
        Random random = new Random();
        Supplier<Integer> supplier = () -> random.nextInt(100);
        Stream<Integer> stream = Stream.generate(supplier).limit(5);
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------*/

    /*
     * Stream 的中間操作
     *
     * map 或者 filter 會叢輸入流中獲取每個元素，並且再輸出流中得到一個結果，這些操作沒有內部狀態，稱為無狀態操作
     * reduce、sum、max 這些操作都需要內部狀態來累積計算結果，所以稱為有狀態操作
     *
     * 無狀態操作
     * map/mapToXxx
     * flatMap/flatMapToXxx
     * filter
     * peek
     * 有狀態操作
     * distinct
     * sorted
     * limit/skip
     * */

    /**
     * map 方法所接受的參數就是一個 Function 對象
     */
    public void mapBase() {
        String[] arr = {"1", "2", "3"};

        Stream<String> s1 = Arrays.stream(arr);
        Stream<Integer> s2 = s1.map(Integer::valueOf);

        // System.out.println(s2.collect(Collectors.toList())); 無法這樣用，會拋出 IllegalStateException，stream 已被關閉

        // 必須改為以下
        Supplier<Stream<String>> test = () -> Arrays.stream(arr);
        System.out.println(test.get().map(Integer::valueOf));

        IntStream.of(1, 2, 3).map(i -> 2 * i).forEach(System.out::println);

        // 可寫成
        String[] arr1 = {"1", "2", "3"};
        Stream<String> s3 = Arrays.stream(arr1);
        s3.mapToLong(Long::parseLong).forEach(System.out::println);
    }

    /**
     * flatMap 可以把 Stream 中的每個元素都映射為一個 Stream，然後再把這多個 Stream 合併為一個 Stream
     */
    public void flatMapBase() {
        // Ex
        Stream<Integer[]> s = Stream.of(new Integer[]{1, 2, 3}, new Integer[]{4, 5, 6}, new Integer[]{7, 8, 9});
        s.forEach(System.out::println);

        // 通過 flatMap 方法
        Stream<Integer> s2 = Stream.of(new Integer[]{1, 2, 3}, new Integer[]{4, 5, 6}, new Integer[]{7, 8, 9}).flatMap(Arrays::stream);
        s2.forEach(System.out::println);
    }

    /**
     * filter 對一個 Stream 中的所有元素一一進行判斷，不滿足條件的就被過濾掉了，剩下的滿足條件元素就構成了一個新的 Stream
     * filter 方法接收的參數是 Predicate
     */
    public void filterBase() {
        IntStream.of(2, 3, 4, 5, 6, 7).filter(i -> i > 3).forEach(System.out::println);
    }

    /**
     * peek 入參是 Consumer，没有返回值，適合對元素內部進行處理
     * peek 方法本身會繼續返回 stream，可以對數據繼續進行處理
     */
    public void peekBase() {
        IntStream.of(2, 3, 4, 5, 6, 7).filter(i -> i > 3).peek(String::valueOf).forEach(System.out::println);
    }

    /**
     * distinct 去重複操作需要獲取到其他元素的值 (比較之後才知道是否重複)，所以這個是有狀態操作
     */
    public void distinctBase() {
        IntStream.of(2, 3, 4, 3, 7, 6, 2, 5, 6, 7).distinct().forEach(System.out::print);
        System.out.println();
    }

    /**
     * sorted 是排序，因為也需要知道其他元素的值，然後才能去重，所以這個也是有狀態操作
     */
    public void sorterBase() {
        IntStream.of(2, 3, 4, 3, 7, 6, 2, 5, 6, 7).distinct().sorted().forEach(System.out::print);
        System.out.println();
    }

    /**
     * limit/skip 配合操作有點像數據中的分頁，skip 表示跳過 n 個元素，limit 表示取出 n 個元素
     * 屬於有狀態操作
     */
    public void limitAndSkipBase() {
        Stream.of('A', 'B', 'C', 'D', 'E', 'F').skip(2).limit(3).forEach(System.out::print);
        System.out.println();

    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------*/

    /*
     * Stream 終止操作
     * 終止操作就是最終計算出結果的操作，只要方法的返回值不是一個 Stream，那就是終止操作，否則就是中間操作
     *
     * 終止操作又分為兩類：
     * 短路操作：不用處理全部元素就可以返回結果
     * findFirst/findAny
     * allMatch/anyMatch/noneMatch
     * 非短路操作：必須處理所有元素才能得到最終結果
     * forEach/forEachOrdered
     * collect/toArray
     * reduce
     * min/max/count
     * */

    /**
     * forEach/forEachOrdered
     * 接收一個 Consumer 類型的參數，完成對參數的消費，不同的是，再並行流中，forEachOrdered 會保證執行順序
     */
    public void forEachAndForEachOrderedBase() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Arrays.stream(arr).parallel().forEach(System.out::print);
        System.out.println();
        Arrays.stream(arr).parallel().forEachOrdered(System.out::print);
    }

    /**
     * collect/toArray 這兩個都是收集作用，可以將執行結果轉為一個 List 或者 Array
     */
    public void collectAndToArrayBase() {
        List<Integer> list = Stream.of(1, 2, 3, 4).filter(p -> p > 2).collect(Collectors.toList());
        System.out.println(list);

        int[] arr = IntStream.of(5, 6, 7, 8).filter(p -> p < 7).toArray();
        Arrays.stream(arr).forEach(System.out::print);
    }

    /**
     * reduce 是 Stream 的一個聚合方法，它可以把一個 Stream 的所有元素按照聚合函數聚合成一個結果
     * reduce 方法傳入的對象是 BinaryOperator 介面，它定義了一個 apply 方法，負責把上次累加的結果和本次的元素進行運算，並返回累加的結果
     */
    public void reduceBase() {
        Optional<Integer> optional = Stream.of(1, 2, 3, 4).reduce(Integer::sum);
        System.out.println(optional.orElse(-1));

        Optional<String> s = Stream.of("wwwjavaboyorg".split("")).reduce((i, j) -> i + "." + j);
        System.out.println(s.orElse(""));
    }

    /**
     * min/max/count 最大值最小值，統計總個數
     */
    public void minAndMaxAndCountBase() {
        Stream<Integer> s = Stream.of(1, 2, 3, 4);
        long count = s.count();
        System.out.println("count = " + count);

        Stream<Integer> s2 = Stream.of(1, 2, 3, 4);
        Optional<Integer> min = s2.min(Comparator.comparingInt(i -> i));
        System.out.println("min.get() = " + min.get());

        Stream<Integer> s3 = Stream.of(1, 2, 3, 4);
        Optional<Integer> max = s3.max(Comparator.comparingInt(i -> i));
        System.out.println("max.get() = " + max.get());
    }

    /**
     * findFirst/findAny 返回 stream 中的第一個、任意一個元素，findAny 要再並行流中測試才有效果
     */
    public void findFirstAndFindAnyBase() {
        for (int i = 0; i < 10; i++) {
            Optional<Integer> first = Stream.of(1, 2, 3, 4).parallel().findFirst();
            System.out.println("first.get() = " + first.get());
        }
        System.out.println("=============");
        for (int i = 0; i < 10; i++) {
            Optional<Integer> first = Stream.of(1, 2, 3, 4, 5, 6, 7).parallel().findAny();
            System.out.println("first.get() = " + first.get());
        }
    }

    /**
     * allMatch/anyMatch/noneMatch
     * 用來判斷所有元素、任意元素或者沒有元素滿足給定條件
     * 這三個方法參數都是一個 Predicate 介面
     */
    public void matchBase() {
        boolean b = Stream.of(1, 2, 3, 4).allMatch(i -> i < 5);
        System.out.println("allMatch b = " + b);

        boolean b1 = Stream.of(1, 2, 3, 4).anyMatch(i -> i == 3);
        System.out.println("anyMatch b1 = " + b1);

        boolean b2 = Stream.of(5, 6, 7, 8).noneMatch(i -> i == 5);
        System.out.println("noneMatch b2 = " + b2);
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------*/

    /*
     * 並行 stream
     * 通常情況下，stream 是單執行緒
     * 在元素數量龐大的情況下，可以透過並行來增加處理速度
     * */
    public void parallelBase() {
        new Random().ints().limit(50).parallel().forEach(i -> {
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        });
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * collect 可以將計算結果重新蒐集到一個集合中，這個集合可以是一個 List/Set 獲取其他，並且可以在收集過程中對數據進行處理
     */
    public void collectBase() {
        List<User3> users = new ArrayList<>();
        users.add(new User3(5, "test", "boy"));
        users.add(new User3(1, "haha", "girl"));
        List<Integer> ages = users.stream().map(User3::getAge).collect(Collectors.toList());
        System.out.println("ages = " + ages);
        List<String> usernames = users.stream().map(User3::getUsername).collect(Collectors.toList());
        System.out.println("usernames = " + usernames);
        Set<String> genders = users.stream().map(User3::getGender).collect(Collectors.toSet());
        System.out.println("genders = " + genders);

        // 獲取 Vector
        List<String> usernamesV = users.stream().map(User3::getUsername).collect(Collectors.toCollection(Vector::new));
        System.out.println("usernamesV = " + usernamesV);
        System.out.println("usernamesV type = " + usernamesV.getClass().getSimpleName());
        // 獲取 TreeSet
        TreeSet<String> gendersS = users.stream().map(User3::getGender).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("gendersS = " + gendersS);
        System.out.println("gendersS type = " + gendersS.getClass().getSimpleName());
        // 獲取字串的統計訊息
        IntSummaryStatistics ageStatistics = users.stream().collect(Collectors.summarizingInt(User3::getAge));
        System.out.println("ageStatistics = " + ageStatistics);
        // 對數據進行分類
        Map<Boolean, List<User3>> map = users.stream().collect(Collectors.partitioningBy(u -> "boy".equals(u.getGender())));
        System.out.println("map = " + map);
        // 按照性別對數據分組
        Map<String, List<User3>> map2 = users.stream().collect(Collectors.groupingBy(User3::getGender));
        System.out.println("map2 = " + map2);
        // 統計男女人數
        Map<String, Long> map3 = users.stream().collect(Collectors.groupingBy(User3::getGender, Collectors.counting()));
        System.out.println("map3 = " + map3);
    }
}

@Data
@AllArgsConstructor
class User3 {

    private int age;
    private String username;
    private String gender;
}