package com.example.web;

import com.google.gson.Gson;
import com.example.beans.UserPayoff;
import com.example.service.AllocationService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Data
class AllocateParams {
    private List<String> userIds;
    private Double totalReward;
}

@Data
class AllocateResponse {

    private int code;
    private List<UserPayoff> userPayoffs;

}

@Slf4j
@RestController
public class Controller {

    private AllocationService allocationServiceImpl;

    @Autowired
    public Controller(AllocationService allocationServiceImpl) {
        this.allocationServiceImpl = allocationServiceImpl;
    }

    @RequestMapping(value = "/allocate", method = RequestMethod.POST)
    public String allocate(@RequestBody AllocateParams allocateParams) {
        var result = Optional.ofNullable(allocationServiceImpl.allocate(allocateParams.getUserIds(),
                allocateParams.getTotalReward()));
        var response = new AllocateResponse();
        if (result.isPresent()) {
            response.setCode(200);  // 成功
            response.setUserPayoffs(result.get());
        } else {
            response.setCode(400);  // 失败
        }
        System.out.println("Thread: " + Thread.currentThread().getId());
        return new Gson().toJson(response);
    }
}
