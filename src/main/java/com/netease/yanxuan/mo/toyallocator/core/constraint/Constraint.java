package com.netease.yanxuan.mo.toyallocator.core.constraint;

import java.util.List;

public interface Constraint {

    List<String> getFeasibleUserIds(List<String> userIds);

}
