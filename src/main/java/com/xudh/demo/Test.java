package com.xudh.demo;

import com.xudh.demo.basic.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName : Test
 * @Description :
 * @Author : xudh
 * @Date: 2021-06-29 17:33
 */
public class Test {
    public static void main(String[] args) {
        Person person = new Person("徐东浩",24);
        System.out.println(person.getName());
        System.out.println("helloworld");

        List<String> strs = new ArrayList<String>();
        strs.add("333");
        List<Object> objs = new ArrayList<>();
        objs.addAll(strs);
        objs.add(1);
        System.out.println(objs.toString());
        String s = strs.get(0);

        Object o = new Object();
//        String s = o;//编译出错 父类变成子类不行

        String s2 = new String();
        Object o2 = s2;//子类可以变成父类


//        ArrayList<Object> objs = new ArrayList<Object>();
//        ArrayList<String> strs = objs;//编译出错

        ArrayList<String> objs1 = new ArrayList<>();
        ArrayList<String> strs1 = objs1;


    }

    private void copyAll(Collection<Object> to, Collection<String> from) {
        to.addAll(from);
    }


}
