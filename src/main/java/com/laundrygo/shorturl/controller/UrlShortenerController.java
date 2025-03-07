package com.laundrygo.shorturl.controller;

import com.laundrygo.shorturl.domain.UrlDto;
import com.laundrygo.shorturl.service.UrlShortenerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/url")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    @ApiOperation(value = "origin url 입력 -> short url 생성", notes = "origin url의 id로 base62 인코딩해서 short url을 생성한다.")
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam String originUrl) {
        String shortUrl = urlShortenerService.shortenUrl(originUrl);
        return ResponseEntity.ok(shortUrl);
    }

    @ApiOperation(value = "short url 입력 -> origin url 반환", notes = "short url을 origin url로 다시 바꾼다.")
    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> decodeShortUrl(@PathVariable String shortUrl) {

        String originalUrl = urlShortenerService.decodeShortUrl(shortUrl);
        if (originalUrl.equals("Not Found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("short url을 찾을 수 없습니다.");
        }
        return ResponseEntity.ok(originalUrl);
    }

    @GetMapping
    public ResponseEntity<List<UrlDto>> getUrls() {
        return ResponseEntity.ok(urlShortenerService.getUrls());
    }
}
