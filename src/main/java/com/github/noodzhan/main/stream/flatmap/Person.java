package com.github.noodzhan.main.stream.flatmap;

import java.util.stream.Stream;

/**
 * 人
 *
 * @author noodzhan
 * @date 2021/4/29 2:32 下午
 */
public class Person {
    private Dog dog;
    private String name;

    public Person(Dog dog, String name) {
        this.dog = dog;
        this.name = name;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Stream<Dog> dogStream = Stream.of(new Person(new Dog("dog 1"), "lisi"), new Person(new Dog("dog 2"), "zhangsan")).flatMap(person -> Stream.of(person.dog));
        dogStream.forEach(e -> System.out.println(e.getName()));
    }
}
