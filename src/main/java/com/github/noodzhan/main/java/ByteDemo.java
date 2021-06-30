package com.github.noodzhan.main.java;

/**
 * 练习比特数据和基本类型的使用
 *
 * @author noodzhan
 * @date 2021/5/9 10:07 上午
 */
public class ByteDemo {
    /**
     * 在计算机系统中，数值一律用补码来表示和存储。原因在于，使用补码，可以将符号位和数值域统一处理；同时，加法和减法也可以统一处理
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 这个是以16进制的方式复制的（补码）,
         * int类型占四个字节
         */
        int aInt = 0xffffffff;
        int bInt = 0xff;
        System.out.printf("aInt: %d,bInt: %d\n", aInt, bInt);
        /**
         * 其实int（4个字节）的类型转化后只会取出末尾的1个字节
         */
        byte aByte = Integer.valueOf(0x11ff).byteValue();
        System.out.printf("aByte: %d\n",aByte);
        /**
         * 比特的最大值(如果是小于-128会报错)
         */
        byte cByte = -128;
        byte dByte = Integer.valueOf(0xff).byteValue();
        System.out.println(dByte);

    }
}
