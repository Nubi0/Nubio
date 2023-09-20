package com.enjoyservice.domain.taste.repository;

import com.enjoyservice.domain.taste.entity.Taste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasteRepository extends JpaRepository<Taste, Long> {
}
