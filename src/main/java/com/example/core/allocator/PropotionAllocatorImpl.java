package com.example.core.allocator;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 按比例分配.
 */
@Component
public class PropotionAllocatorImpl implements Allocator {

    @Override
    public List<Double> allocate(List<Double> weights, Double totalReward) {
        var totalWeight = weights.stream()
                .filter(w -> w >= 0)
                .mapToDouble(w -> w)
                .sum();
        var result = new LinkedList<Double>();
        for (var w: weights) {
            result.add(w >= 0 ? w/totalWeight*totalReward: 0);
        }
        return result;
    }

}
