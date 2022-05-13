package com.itheima;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestTest {
    class MyClass {
        public MyClass() {
            System.out.println("called");
        }
    }

    public static void main(String[] args) throws InstantiationException{
        Class<MyClass> personClass = MyClass.class;
        try {
            MyClass p = personClass.newInstance();
            System.out.println(p);
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
