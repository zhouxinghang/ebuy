package com.ebuy.search;

/**
 * Created by zhouxinghang on 2018/4/26.
 */
public class TestBean {
    private TestBean() {
        System.out.println("TestBean construct begin================>");
    }

    public static TestBean2 getTestBean2() {
        return new TestBean2();
    }
}
