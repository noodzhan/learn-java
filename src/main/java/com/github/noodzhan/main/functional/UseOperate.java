package com.github.noodzhan.main.functional;

import java.util.function.Function;

/**
 * @author noodzhan
 * @date 2021/4/9 3:12 下午
 * @description 具体的业务，动态的传入函数实现
 */
public class UseOperate {
    private Integer a;
    private Integer b;
    {
        a = 1;
        b = 2;
    }

    public Object testFunction(Function param) {
        return param.apply(param);
    }

    public Integer business(Operate operate) {
        return operate.operate(a, b);
    }

    public static void main(String[] args) {
        UseOperate useOperate = new UseOperate();


        //自定义的操作 乘法
        Operate mul = (a, b)-> a*b;
        System.out.println(useOperate.business(mul));

        //自定义的操作 加法
        Operate add  = (a,b)-> a+b;
        System.out.println(useOperate.business(add));

        //自定义除法
        System.out.println(useOperate.business((a, b) -> a / b));


    }






}
