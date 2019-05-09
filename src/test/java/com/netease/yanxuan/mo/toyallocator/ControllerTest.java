package com.netease.yanxuan.mo.toyallocator;

import com.netease.yanxuan.mo.toyallocator.beans.UserPayoff;
import com.netease.yanxuan.mo.toyallocator.service.AllocationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 测试Controller.
 */
@SpringBootTest
public class ControllerTest extends TestCase {

    @MockBean
    private AllocationService allocationServiceImpl;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private List<UserPayoff> allocationResult;

    @Before
    public void contextLoads() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // 分配结果
        allocationResult = new LinkedList<>();
        allocationResult.add(new UserPayoff("10001", 30.0));
        allocationResult.add(new UserPayoff("10002", 30.0));
        allocationResult.add(new UserPayoff("10003", 40.0));
    }

    @Test
    public void allocate() throws Exception {
        when(allocationServiceImpl.allocate(any(), any())).thenReturn(allocationResult);
        var uri = "/allocate";
        // 测试的输入参数
        var jsonString = "{\"userIds\": [\"10001\", \"10002\", \"10003\"], \"totalReward\": 100}";
        var mvcResult = mockMvc.perform(post(uri)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        reset(allocationServiceImpl);
    }
}