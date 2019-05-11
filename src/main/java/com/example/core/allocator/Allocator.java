package com.example.core.allocator;

import java.util.List;

public interface Allocator {

    List<Double> allocate(List<Double> weights, Double totalReward);

}
