package com.netease.yanxuan.mo.toyallocator.core.constraint;

import com.netease.yanxuan.mo.toyallocator.configs.ConstraintConfig;
import com.netease.yanxuan.mo.toyallocator.core.data.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 处理业务逻辑: 考虑分配的最小年龄和最大年龄.
 */
@Component
public class ConstraintImpl implements Constraint {

    private ConstraintConfig constraintConfig;
    private UserData userDataImpl;

    @Autowired
    public ConstraintImpl(ConstraintConfig constraintConfig, UserData userDataImpl) {
        this.constraintConfig = constraintConfig;
        this.userDataImpl = userDataImpl;
    }

    @Override
    public List<String> getFeasibleUserIds(List<String> userIds) {
        var result = new LinkedList<String>();
        for (var userId: userIds) {
            var age = userDataImpl.getUser(userId).getAge();
            if (age >= constraintConfig.getMinAge() && age <= constraintConfig.getMaxAge())
                result.add(userId);
        }
        return result;
    }
}
