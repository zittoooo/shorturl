package com.laundrygo.shorturl.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Url {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String originUrl;

    @Column(unique = true)
    private String shortUrl;

    private Integer count;

}
