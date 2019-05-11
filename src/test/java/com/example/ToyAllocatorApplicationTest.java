package com.example;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ToyAllocatorApplicationTest extends TestCase {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Value("classpath:test-cases/request-body.json")
    private Resource requestBody;

    @Before
    public void contextLoads() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test() throws Exception {
        var jsonString = FileUtils.readFileToString(requestBody.getFile(), "UTF-8");

        // 测试缓存: 发送多次请求
        for (var i=0; i<5; i++) {
            System.out.println(String.format("======== request %d ========", i+1));
            var mvcResult = mockMvc.perform(post("/allocate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
            System.out.println(mvcResult.getResponse().getContentAsString());
        }
    }
}