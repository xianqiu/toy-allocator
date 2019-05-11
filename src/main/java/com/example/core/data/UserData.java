package com.example.core.data;

import com.example.beans.User;

public interface UserData {

    /**
     * 获取用户信息.
     * @param userId: 用户id
     * @return 用户对象
     */
    User getUser(String userId);

}
