package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BackendApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    void bla() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void unauthorized() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "bla")
    void username() throws Exception {
        mockMvc.perform(get("/api/users/me"))
                .andExpect(content().string("bla"));
    }

}
