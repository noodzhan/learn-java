package com.github.noodzhan.main.pattern.prototype;

/**
 * 练习对象的copy
 *
 * @author noodzhan
 * @date 2021/5/11 3:55 下午
 */
public class TUser implements Cloneable{
    private String name;
    private String age;

    public TUser(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "TUser{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        TUser zhangsan = new TUser("张三", "1");
        System.out.println(zhangsan);
        zhangsan.setAge("2");
        Object clone = zhangsan.clone();
        System.out.println((TUser)clone);
    }
}
