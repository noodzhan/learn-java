package com.github.noodzhan.main.java;

/**
 * 实例化条件
 *
 * @author noodzhan
 * @since 2021/6/10 11:20 上午
 */
public class InstantiationDemo extends ParentClass{
    private String app;
    protected String protectedField;
    {
        System.out.println("子类普通代码块");
    }
    static {
        System.out.println("子类静态代码块");
    }
    public InstantiationDemo(String name, String sex) {
        super(name, sex,"123");
        System.out.println("子类构造方法");
        this.protectedField = "123";
    }
    public void test(){

    }

    public static void main(String[] args) {
        InstantiationDemo instantiationDemo = new InstantiationDemo("23","213");
    }
}
//类的实例化顺序：父类静态方法 -> 子类静态方法 -> 父类普通代码块 -> 父类构造方法 -> 子类普通代码块 -> 子类构造方法


//关于重载，优先匹配子类的方法，如果子类没有在匹配父类的方法
