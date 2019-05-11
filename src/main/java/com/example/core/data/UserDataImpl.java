package com.example.core.data;

import com.google.gson.Gson;
import com.example.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import java.io.IOException;


@CacheDefaults(cacheName = "userCache")
@Slf4j
@Component
public class UserDataImpl implements UserData {

    @Value("classpath:./test-cases/users.json")
    private Resource users;

    @CacheResult
    @Override
    public User getUser(String userId) {
        try {
            var jsonString = FileUtils.readFileToString(users.getFile(), "UTF-8");
            log.info("IO operation: read file");
            var userList = new Gson().fromJson(jsonString, UserList.class).getUserList();
            for (var user: userList) {
                if (user.getUserId().equals(userId)) return user;
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
        return null;
    }
}
