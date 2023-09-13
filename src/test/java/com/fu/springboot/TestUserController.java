package com.fu.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Starter.class})
@AutoConfigureMockMvc
public class TestUserController {
    //注入MockMvc对象
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        //构建请求
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/list")
                .contentType("text/html")
                .accept(MediaType.APPLICATION_JSON);
        //发送请求，获取请求结果
        ResultActions perform = mockMvc.perform(builder);
        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        // 表示执行完成后返回相应的结果
        MvcResult mvcResult = perform.andReturn();
        // 得到执行后的响应
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("状态=" + response.getStatus());
        System.out.println("响应数据=" + response.getContentAsString());
    }

    @Test
    public void apiTeste2() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("响应状态: " + mvcResult.getResponse().getStatus());
        System.out.println("响应内容: " + mvcResult.getResponse().getContentAsString());
    }

}
