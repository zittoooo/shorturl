package com.laundrygo.shorturl.controller;

import com.laundrygo.shorturl.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/url")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam String originUrl) {
        String shortUrl = urlShortenerService.shortenUrl(originUrl);
        return ResponseEntity.ok(shortUrl);
    }


}
