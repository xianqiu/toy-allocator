package com.netease.yanxuan.mo.toyallocator;

import com.google.gson.Gson;
import com.netease.yanxuan.mo.toyallocator.beans.User;
import com.netease.yanxuan.mo.toyallocator.core.constraint.Constraint;
import com.netease.yanxuan.mo.toyallocator.core.data.UserData;
import com.netease.yanxuan.mo.toyallocator.core.data.UserList;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;

import java.util.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ConstraintImplTest extends TestCase {

    @MockBean
    private UserData userDataImpl;
    @Autowired
    private Constraint constraintImpl;
    @Value("classpath:test-cases/users.json")
    private Resource users;
    private Map<String, User> userMap = new HashMap<>();

    @Before
    public void loadContext() throws Exception {
        var jsonString = FileUtils.readFileToString(users.getFile(), "UTF-8");
        var userList = new Gson().fromJson(jsonString, UserList.class).getUserList();
        for (var user: userList) {
            userMap.put(user.getUserId(), user);
        }
    }

    @Test
    public void getFeasibleUserIds() {
        for (var userId: userMap.keySet()) {
            when(userDataImpl.getUser(userId)).thenReturn(userMap.get(userId));
        }
        var result = constraintImpl.getFeasibleUserIds(new LinkedList<>(userMap.keySet()));
        Assert.assertEquals(Set.of("10001", "10002", "10007", "10008", "10009"), new HashSet<>(result));
    }

    @After
    public void clean() {

    }

}
