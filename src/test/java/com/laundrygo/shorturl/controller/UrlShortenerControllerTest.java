package com.laundrygo.shorturl.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortenerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShortenAndDecodeUrl() throws Exception {
        // 테스트 1번
        MvcResult result = mockMvc.perform(post("/url/shorten?originUrl=https://www.laundrygo.com/"))
                .andExpect(status().isOk())
                .andReturn();
        String shortUrl = result.getResponse().getContentAsString();
        assertTrue(shortUrl.length() <= 8);

        result = mockMvc.perform(get("/url/" + shortUrl))
                .andExpect(status().isOk())
                .andReturn();
        String originUrl = result.getResponse().getContentAsString();
        Assertions.assertEquals("https://www.laundrygo.com/", originUrl);


        // 테스트 2번
        result = mockMvc.perform(post("/url/shorten?originUrl=https://example.com"))
                .andExpect(status().isOk())
                .andReturn();
        shortUrl = result.getResponse().getContentAsString();
        assertTrue(shortUrl.length() <= 8);

        result = mockMvc.perform(get("/url/" + shortUrl))
                .andExpect(status().isOk())
                .andReturn();
        originUrl = result.getResponse().getContentAsString();
        Assertions.assertEquals("https://example.com", originUrl);
    }

}