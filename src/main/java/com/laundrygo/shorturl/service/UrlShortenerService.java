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

        if (urlRepository.findByOriginUrl(originUrl).isPresent()) {
            Url findUrl = urlRepository.findByOriginUrl(originUrl).get();
            findUrl.setCount(findUrl.getCount()+1);
            urlRepository.save(findUrl);
            return findUrl.getShortUrl();
        } else {
            Url url = new Url();
            url.setOriginUrl(originUrl);
            url.setCount(1);
            urlRepository.save(url);
            String encodeUrl = Util.encoding(url.getId());
            url.setShortUrl(encodeUrl);
            urlRepository.save(url);
            return url.getShortUrl();
        }
    }


    public String decodeShortUrl(String shortUrl) {
        //Optional<Url> findUrl = urlRepository.findByShortUrl(shortUrl);
        //if (findUrl.isPresent()) {
            int id = Util.decode(shortUrl);
            return urlRepository.findById(id).get().getOriginUrl();
        //}
        //return "Not Found";
    }
}
