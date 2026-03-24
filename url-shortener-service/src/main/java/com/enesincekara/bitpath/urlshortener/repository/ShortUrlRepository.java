package com.enesincekara.bitpath.urlshortener.repository;

import com.enesincekara.bitpath.urlshortener.entity.ShortUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, String> {
}