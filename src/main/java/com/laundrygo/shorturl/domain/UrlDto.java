package com.laundrygo.shorturl.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UrlDto {
    private String originUrl;
    private String shortUrl;
    private Integer count;
}
