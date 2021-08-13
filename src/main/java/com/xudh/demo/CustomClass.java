package com.xudh.demo;

/**
 * @ClassName : CustomClass
 * @Description :
 * @Author : xudh
 * @Date: 2021-07-02 15:50
 */
public class CustomClass<T> {
    private void copyTo(CustomClass<? extends T> to, CustomClass<? extends T> from) {

    }

    public static void main(String[] args) {
        CustomClass<Object> customClass1 = new CustomClass<>();
        CustomClass<String> customClass2 = new CustomClass<>();
        customClass1.copyTo(customClass1, customClass2);

    }
}


