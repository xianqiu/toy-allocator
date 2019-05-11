package com.example.beans;

import lombok.Data;

@Data
public class UserPayoff {
    private String userId;
    private Double gain;

    public UserPayoff(String userId, Double gain) {
        this.userId = userId;
        this.gain = gain;
    }
}
