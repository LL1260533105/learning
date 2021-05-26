package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @Author: admin
 * @Date: 2021/5/25 9:33
 * @Version: 1.0
 */
public class JDK8StreamTest {

    public static List<String> stringList1;
    public static List<Integer> integerList;
    public static List<people> peopleList;

    static {
        stringList1 = List.of("ss", "aa", "bb", "cc", "dd", "ss", "aa");
        integerList = List.of(1, 2, 3, 4, 5, 6);
        peopleList = List.of(new people("李四", "男", 12), new people("王五", "男", 19)
                , new people("张三", "女", 25) , new people("宋一", "女", 25));
    }

    public static void main(String[] args) {
        System.out.println(stringList1);
        // 元素去重
        stringList1 = stringList1.stream().distinct().collect(Collectors.toList());
        System.out.println(stringList1);
        // 元素截取
        stringList1 = stringList1.stream().limit(2).collect(Collectors.toList());
        System.out.println(stringList1);
        // 元素跳过
        stringList1 = stringList1.stream().skip(1).collect(Collectors.toList());
        System.out.println(stringList1);

        // Map方法的效果是，各个数组并分别映射成一个流，而不是映射成流的内容
        List<Stream<String>> collect = stringList1.stream().map(x -> x.split("")).map(Arrays::stream).distinct().collect(Collectors.toList());

        // flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容
        List<String> collect1 = stringList1.stream().map(x -> x.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect + "\n");
        System.out.println(collect1);

        // 数值流操作  找出1-100前五个勾股数
        Stream<int[]> stream = IntStream.rangeClosed(1, 100).boxed().flatMap(a ->
                IntStream.rangeClosed(a, 100).filter(b ->
                        Math.sqrt(a * a + b * b) % 1 == 0
                ).mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
        );
        stream.limit(5).forEach(x -> System.out.println(x[0] + "," + x[1] + "," + x[2]));

        // 利用 reduce 对集合进行归集相关的操作 sum max min 等一些操作
        int sum = integerList.stream().reduce(Integer::sum).orElse(1);
        System.out.println("求和结果为：" + sum);

        /*
         创建流的几种方式
         1. 由值创建  eg:Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
                         stream.map(String::toUpperCase).forEach(System.out::println);
         2. 由数组创建 eg: int[] numbers = {2, 3, 5, 7, 11, 13};
                           int sum = Arrays.stream(numbers).sum();
         3. 由文件生成： eg： Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){};
         */

        /*
         数值流
          java1.8引入了三种原始类型特化流  IntStream、DoubleStream和 LongStream， 分别将流中的元素特化为int、long和double，从而避免了暗含的装箱成本。
          如何将特化流转换成对象流呢   可以使用 boxed 方法。
          eg： Stream<Integer> boxed = IntStream.rangeClosed(1, 100).boxed(); 将 IntStream 转化为 Stream<Integer>。
         */

        // 分组 groupingBy 的使用
        Map<String, List<Integer>> collect2 = integerList.stream().collect(groupingBy(item -> {
            if (item <= 3) {
                return "小于等于3";
            } else {
                return "大于3";
            }
        }));
        System.out.println(collect2);

        // 分组后对子集做一些操作  例如下面求最大值项
        Map<String, Optional<Integer>> collect3 = integerList.stream().collect(groupingBy(item -> {
            if (item <= 3) {
                return "小于等于3";
            } else {
                return "大于3";
            }
        }, maxBy(Integer::max)));

        System.out.println(collect3);

        // 使用 collectingAndThen 将收集结果转换成其他类型,例如下面Optional转换成Integer类型
        Map<String, Integer> collect4 = integerList.stream().collect(groupingBy(item -> {
            if (item <= 3) {
                return "小于等于3";
            } else {
                return "大于3";
            }
        }, collectingAndThen(maxBy(Integer::max), Optional::get)));

        System.out.println(collect4);

        // groupingBy收集器联合使用其他收集器 eg：mapping，summingInt 等
        // groupingBy收集器 与 summingInt 收集器联合使用，根据性别分组，合计年龄总和
        Map<String, Integer> collect5 = peopleList.stream().collect(groupingBy(
                people::getSex, summingInt(people::getAge)
        ));
        System.out.println(collect5);

        // groupingBy收集器 与 mapping收集器联合使用，根据sex分组，看分组后people中是否都有已成年和未成年的
        // 这种写法对于返回数据的数据类型没有保障，我们可以使用 toCollection 进行数据类型的保障
        Map<String, Set<String>> collect6 = peopleList.stream().collect(groupingBy(
                people::getSex, mapping(item -> {
                    if (item.getAge() > 18) {
                        return "已经成年";
                    } else {
                        return "未成年";
                    }
                }, toSet())
//                }, toCollection(HashSet::new))  // 这样能够保障返回数据的数据类型是hashSet，其他的同理
        ));
        System.out.println(collect6);

        // 找出2 - 20 之间的质数
        Map<Boolean, List<Integer>> prime = getPrime(20);
        System.out.println(prime);
    }

    // 找出给定范围内的质数 和 非质数
    private static Map<Boolean, List<Integer>> getPrime(int end){
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, end).boxed().collect(partitioningBy(item -> isPrime(item)));
        return collect;
    }

    private static boolean isPrime(int number) {
        return IntStream.range(2, number).boxed().noneMatch(item -> number % item == 0);
    }
}


class people{
    public String name;
    public String sex;
    public int age;

    public people(String name, String sex,int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}