package com.github.noodzhan.main.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 1。 测试hashMap能否放key为null的值;测试结果hashmap是可以存放key值为null的键值对。
 * 2。 测试hashset也是可以存放null的，如果存放两个null值，结果还是可以去重的。
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/7/19 10:56 上午
 */
public class HashMapDemo {
    static class ObjectOOM{

    }
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put(null, "qw3");
        map.put(null, "2");
        System.out.println(map.size());
        System.out.println(map.get(null));


        HashSet<ObjectOOM> set = new HashSet<>();
        set.add(null);
        set.add(null);
        System.out.println(set.size());
        System.out.println(Arrays.asList(set.toArray()));

        while(true){
            set.add(new ObjectOOM());
        }

    }
}
