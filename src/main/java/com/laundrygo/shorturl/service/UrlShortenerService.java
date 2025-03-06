package com.laundrygo.shorturl.service;

import com.laundrygo.shorturl.Util;
import com.laundrygo.shorturl.domain.Url;
import com.laundrygo.shorturl.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private final UrlRepository urlRepository;

    public String shortenUrl(String originUrl) {
        Optional<Url> existUrl = urlRepository.findByOriginUrl(originUrl);
        if (existUrl.isPresent()) {
            Url findUrl = existUrl.get();
            findUrl.setCount(findUrl.getCount()+1);
            urlRepository.save(findUrl); // 업데이트 저장
            return findUrl.getShortUrl();
        }
        // 새로운 Url 저장
        Url url = new Url();
        url.setOriginUrl(originUrl);
        url.setCount(1);
        urlRepository.save(url); // id 생성을 위한 저장
        // id를 기반으로 short url 생성 후 저장
        String encodeUrl = Util.encoding(url.getId());
        url.setShortUrl(encodeUrl);
        urlRepository.save(url);

        return encodeUrl;
    }

    public String decodeShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .map(Url::getOriginUrl)
                .orElse("Not Found");
    }
}
