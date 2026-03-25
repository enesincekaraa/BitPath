package com.enesincekara.bitpath.urlshortener.repository;

import com.enesincekara.bitpath.urlshortener.entity.ClickEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickEventRepository extends JpaRepository<ClickEvent, String> {
}
