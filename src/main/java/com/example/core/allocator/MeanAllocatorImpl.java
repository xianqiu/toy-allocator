package com.example.core.allocator;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


/**
 * 平均分配.
 */
@Component
public class MeanAllocatorImpl implements Allocator {

    @Override
    public List<Double> allocate(List<Double> weights, Double totalReward) {
        var size = weights.size();
        var result = new LinkedList<Double>();
        for (var i=0; i<size; i++) {
            result.add(totalReward/size);
        }
        return result;
    }
}
