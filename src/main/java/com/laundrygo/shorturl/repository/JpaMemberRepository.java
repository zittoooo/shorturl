package com.laundrygo.shorturl.repository;

import com.laundrygo.shorturl.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Integer> {
}
