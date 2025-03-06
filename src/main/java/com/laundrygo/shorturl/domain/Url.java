package com.laundrygo.shorturl.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Url {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String originUrl;

    private String shortUrl;

    private Integer count;

}
