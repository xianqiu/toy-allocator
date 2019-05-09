package com.netease.yanxuan.mo.toyallocator;

import com.netease.yanxuan.mo.toyallocator.beans.User;
import com.netease.yanxuan.mo.toyallocator.core.data.UserData;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDataImplTest extends TestCase {

    @Autowired
    private UserData userDataImpl;

    @Test
    public void getUser() {
        var expected = new User();
        expected.setUserId("10005");
        expected.setGender("male");
        expected.setAge(66);
        Assert.assertEquals(expected, userDataImpl.getUser("10005"));
    }
}
