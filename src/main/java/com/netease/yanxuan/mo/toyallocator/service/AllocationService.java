package com.netease.yanxuan.mo.toyallocator.service;

import com.netease.yanxuan.mo.toyallocator.beans.UserPayoff;

import java.util.List;


public interface AllocationService {

    /**
     * @param userIds 用户id的列表
     * @param totalReward 待分配的奖金
     * @return 分配结果
     */
    List<UserPayoff> allocate(List<String> userIds, Double totalReward);

}
