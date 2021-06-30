package com.github.noodzhan.main.java;

/**
 * TODO
 *
 * @author noodzhan
 * @since 2021/6/10 11:22 上午
 */
public class ParentClass {
    private String name;
    private String sex;
    protected String protectedField;
    private static ByteDemo byteDemo;
    {
        System.out.println("父类普通代码块");
    }
    static{
        System.out.println("父类静态代码块");
    }
    public ParentClass(String name, String sex,String protectedField) {
        System.out.println("父类构造方法");
        this.name = name;
        this.sex = sex;
        this.protectedField = protectedField;
    }

}
