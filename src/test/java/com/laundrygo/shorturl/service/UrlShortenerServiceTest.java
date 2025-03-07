package com.laundrygo.shorturl.service;

import com.laundrygo.shorturl.domain.Url;
import com.laundrygo.shorturl.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlShortenerServiceTest {
    @Autowired
    private UrlRepository urlRepository;  // 가짜 데이터 저장소
    @Autowired
    private UrlShortenerService urlShortenerService;  // 테스트할 서비스
    private final String originUrl = "https://naver.com";

    @Test
    void countTest() {
        // 같은 originUrl로 2번 요청했을 때 count 값 증가 확인
        urlShortenerService.shortenUrl(originUrl);
        Optional<Url> findUrl = urlRepository.findByOriginUrl(originUrl);
        assertEquals(1, findUrl.get().getCount());

        urlShortenerService.shortenUrl(originUrl);
        findUrl = urlRepository.findByOriginUrl(originUrl);
        assertEquals(2, findUrl.get().getCount());
    }

}