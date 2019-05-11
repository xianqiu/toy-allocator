package com.example.core.data;

import com.example.beans.User;
import lombok.Data;

import java.util.List;

@Data
public class UserList {
    private List<User> userList;
}
