package com.ebuy.search;

/**
 * Created by zhouxinghang on 2018/4/26.
 */
public class TestBeanFactory {
    private TestBeanFactory() {
        System.out.println("TestBeanFactory ==========>");
    }

    public TestBean2 getTestBean() {
        return new TestBean2();
    }

}
