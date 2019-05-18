package com.example.service;

import com.example.beans.UserPayoff;
import com.example.core.allocator.Allocator;
import com.example.core.constraint.Constraint;
import com.example.core.data.UserData;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

@Component
public class AllocationServiceImpl implements AllocationService {

    private UserData userDataImpl;
    private Constraint constraintImpl;
    private Allocator propotionAllocatorImpl;  // 按比例分配

    @Autowired
    public AllocationServiceImpl(UserData userDataImpl,
                                 Constraint constraintImpl, Allocator propotionAllocatorImpl) {
        this.userDataImpl = userDataImpl;
        this.constraintImpl = constraintImpl;
        this.propotionAllocatorImpl = propotionAllocatorImpl;
    }

    @Override
    public List<UserPayoff> allocate(List<String> userIds, Double totalReward) {

        System.out.println("Thread: " + Thread.currentThread().getName());

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

    @Async
    public Future<List<UserPayoff>> allocateAsync(List<String> userIds, Double totalReward) {
        System.out.println("Thread: " + Thread.currentThread().getName());
        return new AsyncResult<>(allocate(userIds, totalReward));
    }

}
