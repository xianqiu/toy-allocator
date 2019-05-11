package com.example.core.constraint;

import java.util.List;

public interface Constraint {

    /** 按业务约束过滤无效的用户.
     * @param userIds: 用户id列表
     * @return 满足分配条件的用户id列表
     */
    List<String> getFeasibleUserIds(List<String> userIds);

}
