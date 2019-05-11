package com.example.core.allocator;

import java.util.List;

public interface Allocator {

    /** 资产分配算法.
     * @param weights: 用户权重的列表
     * @param totalReward: 总资产
     * @return 用户分配到的资产
     */
    List<Double> allocate(List<Double> weights, Double totalReward);

}
