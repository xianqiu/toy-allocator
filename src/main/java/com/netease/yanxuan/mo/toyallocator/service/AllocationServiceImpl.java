package com.netease.yanxuan.mo.toyallocator.service;

import com.netease.yanxuan.mo.toyallocator.beans.UserPayoff;
import com.netease.yanxuan.mo.toyallocator.core.allocator.Allocator;
import com.netease.yanxuan.mo.toyallocator.core.constraint.Constraint;
import com.netease.yanxuan.mo.toyallocator.core.data.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

@Component
public class AllocationServiceImpl implements AllocationService {

    private UserData userDataImpl;
    private Constraint constraintImpl;
    private Allocator propotionAllocatorImpl;

    @Autowired
    public AllocationServiceImpl(UserData userDataImpl,
                                 Constraint constraintImpl, Allocator propotionAllocatorImpl) {
        this.userDataImpl = userDataImpl;
        this.constraintImpl = constraintImpl;
        this.propotionAllocatorImpl = propotionAllocatorImpl;
    }

    @Override
    public List<UserPayoff> allocate(List<String> userIds, Double totalReward) {
        var feasibleUserIds = constraintImpl.getFeasibleUserIds(userIds);
        var weights = new LinkedList<Double>();
        for (var userId: feasibleUserIds) {
            weights.add(Double.valueOf(userDataImpl.getUser(userId).getAge()));
        }
        var payoffs = propotionAllocatorImpl.allocate(weights, totalReward);
        var userPayoffList = new LinkedList<UserPayoff>();
        for (var i=0; i<feasibleUserIds.size(); i++) {
            var userId = feasibleUserIds.get(i);
            userPayoffList.add(new UserPayoff(userId, payoffs.get(i)));
        }
        return userPayoffList;
    }

}
