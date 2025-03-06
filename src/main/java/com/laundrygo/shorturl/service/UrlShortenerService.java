package com.laundrygo.shorturl.service;

import com.laundrygo.shorturl.Util;
import com.laundrygo.shorturl.domain.Url;
import com.laundrygo.shorturl.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private final UrlRepository urlRepository;

    public String shortenUrl(String originUrl) {
        Url url = new Url();

        if (urlRepository.findByOriginUrl(originUrl).isPresent()) {
            Url findUrl = urlRepository.findByOriginUrl(originUrl).get();
            findUrl.setCount(findUrl.getCount()+1);
            urlRepository.save(findUrl);
            return findUrl.getShortUrl();
        } else {
            url.setOriginUrl(originUrl);
            url.setCount(1);
            String encodeUrl = Util.encoding(originUrl);
            // 중복되는 short url 생성되면 중복되지 않게 생성될 때까지 다시 생성하기
            while (urlRepository.existsByShortUrl(encodeUrl)) {
                encodeUrl = Util.encoding(originUrl);
            }
            url.setShortUrl(encodeUrl);
            urlRepository.save(url);
            return url.getShortUrl();
        }
    }

}
