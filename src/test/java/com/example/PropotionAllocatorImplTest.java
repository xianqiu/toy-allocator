package com.example;

import com.example.core.allocator.Allocator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PropotionAllocatorImplTest extends TestCase {

    @Autowired
    private Allocator propotionAllocatorImpl;

    @Test
    public void allocate() {
        var weights = List.of(10.0, 20.0, 30.0, 40.0);
        var totalReward = 200.0;
        var result = propotionAllocatorImpl.allocate(weights, totalReward);
        Assert.assertEquals(List.of(20.0, 40.0, 60.0, 80.0), result);
    }
}
