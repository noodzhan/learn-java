package com.github.noodzhan.main.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛型类
 *
 * @author noodzhan
 * @since 2021/6/11 3:37 下午
 */
public class GenericClass<T extends Map,R extends String> {

    private T t;

    public GenericClass(T t) {
        this.t = t;
    }

    public R apply(String key) {
        return (R) t.get(key);
    }


    /**
     * 泛型方法，主要是为了解决参数和返回值可变的情况，定义的方法
     * @param e 参数
     * @param <E> 定义的泛型占位符（用于声明类型）
     * @return 返回值也是泛型
     */
    public <E> E genericFunction(E e) {
        System.out.println(e);
        return e;
    }


    public <T extends String> T getT(T e) {
        System.out.println(e);
        return e;
    }

    public static void main(String[] args) {
        Map<Object, Object> test = new HashMap<>();
        test.put("12", "12");

        GenericClass mapGenericClass = new GenericClass<Map,String>(test);
        String apply = mapGenericClass.apply("12");
        System.out.println(apply);

        mapGenericClass.genericFunction(new Long(1L));

    }

}
