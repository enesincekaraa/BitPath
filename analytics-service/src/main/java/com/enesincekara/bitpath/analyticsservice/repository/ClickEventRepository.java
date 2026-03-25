package com.enesincekara.bitpath.analyticsservice.repository;

import com.enesincekara.bitpath.analyticsservice.entity.ClickEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickEventRepository extends JpaRepository<ClickEvent, String> {
}
