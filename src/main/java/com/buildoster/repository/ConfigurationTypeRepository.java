package com.buildoster.repository;

import com.buildoster.model.ConfigurationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigurationTypeRepository extends JpaRepository<ConfigurationType,Long> {

    List<ConfigurationType> findByConfigurationId(Long id);
}
