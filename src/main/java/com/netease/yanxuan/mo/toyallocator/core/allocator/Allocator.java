package com.netease.yanxuan.mo.toyallocator.core.allocator;

import java.util.List;

public interface Allocator {

    List<Double> allocate(List<Double> weights, Double totalReward);

}
