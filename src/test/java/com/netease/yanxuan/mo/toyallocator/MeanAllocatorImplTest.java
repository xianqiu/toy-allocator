package com.netease.yanxuan.mo.toyallocator;

import com.netease.yanxuan.mo.toyallocator.core.allocator.Allocator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MeanAllocatorImplTest extends TestCase {

    @Autowired
    private Allocator meanAllocatorImpl;

    @Test
    public void allocate() {
        var weights = List.of(10.0, 20.0, 30.0, 40.0);
        var totalReward = 200.0;
        var result = meanAllocatorImpl.allocate(weights, totalReward);
        Assert.assertEquals(List.of(50.0, 50.0, 50.0, 50.0), result);
    }
}
