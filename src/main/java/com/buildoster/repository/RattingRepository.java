package com.buildoster.repository;

import com.buildoster.model.Ratting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RattingRepository extends JpaRepository<Ratting,Long> {
}
