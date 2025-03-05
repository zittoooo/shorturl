package com.laundrygo.shorturl.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author laundrygo
 * @version 0.1.0
 * @since 2021/06/22 7:01 오후
 */
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
