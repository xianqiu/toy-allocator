package com.netease.yanxuan.mo.toyallocator;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public abstract class TestCase {

    @Before
    public void init() {
        System.out.println(String.format(">>> %s: Test Environment is OK.", getClass().getName()));
    }

}
