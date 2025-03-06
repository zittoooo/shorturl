package com.laundrygo.shorturl.repository;

import com.laundrygo.shorturl.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Integer> {
    Optional<Url> findByShortUrl(String shortUrl);
    Optional<Url> findByOriginUrl(String originalUrl);

    boolean existsByShortUrl(String shortUrl);

}
