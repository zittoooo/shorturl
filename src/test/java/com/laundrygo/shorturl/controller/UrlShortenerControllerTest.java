package com.laundrygo.shorturl.controller;

import com.laundrygo.shorturl.service.UrlShortenerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortenerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlShortenerService urlShortenerService;

    @Test
    void testShortenAndRetrieveUrl() throws Exception {
        // 테스트 1번
        MvcResult result = mockMvc.perform(post("/url/shorten?originUrl=https://google.com"))
                .andExpect(status().isOk())
                .andReturn();

        String shortUrl = result.getResponse().getContentAsString();

        MvcResult retrieveResult = mockMvc.perform(get("/url/" + shortUrl))
                .andExpect(status().isOk())
                .andReturn();

        String retrievedUrl = retrieveResult.getResponse().getContentAsString();
        Assertions.assertEquals("https://google.com", retrievedUrl);


        // 테스트 2번
        result = mockMvc.perform(post("/url/shorten?originUrl=https://example.com"))
                .andExpect(status().isOk())
                .andReturn();

        shortUrl = result.getResponse().getContentAsString();

        retrieveResult = mockMvc.perform(get("/url/" + shortUrl))
                .andExpect(status().isOk())
                .andReturn();

        retrievedUrl = retrieveResult.getResponse().getContentAsString();
        Assertions.assertEquals("https://example.com", retrievedUrl);
    }
}