package designPatterns.builder;

/**
 * 构建器模式： 使用简单对象构建复杂对象并使用算法，这种设计模式属于创建型模式，
 *             在这种设计模式中，构建器类逐步构建最终对象。
 *
 *  自我理解：简单的讲就是将复杂对象创建简单化，可以根据自己的需要进行参数的初始化，不用当参数很多的传一堆参数
 */
public class Person {
    private Person(){}

    /**
     * 年龄
     */
    private String age;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    public static Person buildBasicInfo(String name){
        Person person = new Person();
        return person.buildName(name);
    }

    private  Person buildName(String name){
        this.name = name;
        return this;
    }

    public Person buildAge(String age){
        this.age = age;
        return this;
    }

    public Person buildSex(String sex){
        this.sex = sex;
        return this;
    }

    public static void main(String[] args) {
        // 构建器模式常用链式编程的方式构建复杂对象
        Person person = Person.buildBasicInfo("Andy").buildAge("18").buildSex("boy");
    }
}
