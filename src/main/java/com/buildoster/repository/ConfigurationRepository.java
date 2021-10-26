package com.buildoster.repository;

import com.buildoster.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration,Long> {
}
