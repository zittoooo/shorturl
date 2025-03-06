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
            url.setShortUrl(encodeUrl);
            urlRepository.save(url);
            return url.getShortUrl();
        }
    }

}
